package com.intellij.plugin.powershell.psi

import com.intellij.plugin.powershell.psi.PowerShellTypes.*
import com.intellij.psi.tree.TokenSet

/**
 * Andrey 17/07/17.
 */
object PowerShellTokenTypeSets {

  val KEYWORDS = TokenSet.create(BEGIN, BREAK, CATCH, CLASS, CONTINUE, DATA, DEFINE, DO, DYNAMICPARAM, ELSE, ELSEIF, END, EXIT, FILTER, FINALLY, FOR,
                                 FOREACH, FROM, FUNCTION, IF, IN, INLINESCRIPT, PARALLEL, PARAM, PROCESS, RETURN, SWITCH, THROW, TRAP, TRY, UNTIL, USING, VAR, WHILE, WORKFLOW,
                                 CONFIGURATION, THIS, HIDDEN, STATIC, ENUM)
  val COMMENTS = TokenSet.create(COMMENT, REQUIRES_COMMENT_START)
  val STRINGS = TokenSet.create(EXPANDABLE_STRING_PART, DQ_OPEN, DQ_CLOSE, VERBATIM_STRING, EXPANDABLE_HERE_STRING_START, EXPANDABLE_HERE_STRING_END, EXPANDABLE_HERE_STRING_PART, VERBATIM_HERE_STRING)
  val NUMBERS = TokenSet.create(DEC_INTEGER, HEX_INTEGER, REAL_NUM)
  val IDENTIFIERS = TokenSet.create(SIMPLE_ID, BRACED_ID, VAR_ID, GENERIC_ID_PART, DS, QMARK, HAT)
  private val IDENTIFIERS_ALLOWED_AS_KEYWORD = TokenSet.create(PARAM, UNTIL, WORKFLOW, END, DEFINE, FINALLY, PARALLEL, CONTINUE, BEGIN, DYNAMICPARAM, IN,
                                                               PROCESS, BREAK, ELSE, INLINESCRIPT, CATCH, THIS, HIDDEN, FOREACH, STATIC)
  val MEMBER_IDENTIFIERS = TokenSet.orSet(TokenSet.create(SIMPLE_ID), IDENTIFIERS_ALLOWED_AS_KEYWORD)
  val BRACED_VARIABLE_IDENTIFIERS = TokenSet.create(BRACED_ID)
  val SIMPLE_VARIABLE_IDENTIFIERS = TokenSet.create(SIMPLE_ID, VAR_ID, THIS, QMARK, HAT, DS)
  val FUNCTION_IDENTIFIERS = TokenSet.orSet(TokenSet.create(SIMPLE_ID, GENERIC_ID_PART, THIS, QMARK, HAT, DS, DOT), IDENTIFIERS_ALLOWED_AS_KEYWORD)
  val OPERATORS = TokenSet.create(OP_C, OP_AND, OP_OR, OP_XOR, OP_BAND, OP_BOR, OP_BXOR, PLUS, DASH, STAR, DIV, PERS)
  val PROCESS_AS_WORD_TOKENS = TokenSet.create(QMARK, DS, HAT, LP, RP, DOT)
}
