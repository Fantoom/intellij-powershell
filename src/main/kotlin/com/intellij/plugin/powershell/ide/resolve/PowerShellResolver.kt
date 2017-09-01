package com.intellij.plugin.powershell.ide.resolve

import com.intellij.plugin.powershell.psi.PowerShellCallableReference
import com.intellij.plugin.powershell.psi.PowerShellComponent
import com.intellij.plugin.powershell.psi.PowerShellReference
import com.intellij.plugin.powershell.psi.PowerShellVariable
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.impl.source.resolve.ResolveCache
import com.intellij.psi.scope.BaseScopeProcessor
import com.intellij.psi.util.PsiTreeUtil
import java.util.*

/**
 * Andrey 21/08/17.
 */
class PowerShellResolver<T> : ResolveCache.AbstractResolver<T, List<PsiElement>> where T : PowerShellReference {
  companion object {
    val INSTANCE = PowerShellResolver<PowerShellReference>()
  }

  private fun resolveReference(ref: T): List<PsiElement> {
    val result = ArrayList<PowerShellComponent>()
    val resolveProcessor = if (ref is PowerShellCallableReference) {//  $function: F  # invokes function F -> also callable reference (when ns== "function")
      PowerShellCallableResolveProcessor(ref)
    } else {
      PowerShellVariableResolveProcessor(ref)
    }
    PsiTreeUtil.treeWalkUp(resolveProcessor, ref, null, ResolveState.initial())
    val res = resolveProcessor.getResult()
    if (res != null) result.add(res)
    return result
  }

  override fun resolve(ref: T, incompleteCode: Boolean): List<PsiElement> {

    return resolveReference(ref)
  }
}

abstract class PowerShellResolveProcessor<out R : PowerShellReference>(protected val myRef: R) : BaseScopeProcessor() {
  protected var myResult: PowerShellComponent? = null //todo should navigate to closest declaration (of function or variable)

  fun getResult(): PowerShellComponent? {
    return myResult
  }

}

class PowerShellCallableResolveProcessor(ref: PowerShellCallableReference) : PowerShellResolveProcessor<PowerShellCallableReference>(ref) {

  override fun execute(element: PsiElement, state: ResolveState): Boolean {
    if (element is PowerShellComponent) {
      if (element.name.equals(myRef.canonicalText, true) && (element !is PowerShellVariable || "function".equals(element.getNamespace(), true))) {
//      if (PowerShellResolveUtil.areNamesEqual(element, myRef)) {
        myResult = element
        return false
      }
//      }
    }
    return true
  }

}

class PowerShellVariableResolveProcessor(ref: PowerShellReference) : PowerShellResolveProcessor<PowerShellReference>(ref) {

  override fun execute(element: PsiElement, state: ResolveState): Boolean {
    if (element is PowerShellComponent) {
      val refName = myRef.canonicalText
      if (element is PowerShellVariable) {
        if (element.getQualifiedName().equals(refName, true)) {
          myResult = element
          return false
        }
      } else if (element.name.equals(refName, true)) {
        myResult = element
        return false
      }
    }
    return true
  }


}
