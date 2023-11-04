package com.intellij.plugin.powershell.lang;

import com.intellij.plugin.powershell.lang.parser.PowerShellParserDefinition
import com.intellij.testFramework.ParsingTestCase

import java.io.File;

private val TEST_DATA_PATH = "src" + File.separatorChar + "test" + File.separatorChar + "resources" + File.separatorChar + "testData"

class PowerShellParserTest : ParsingTestCase("parser", "ps1", PowerShellParserDefinition()) {

  override fun getTestDataPath() = TEST_DATA_PATH

  override fun includeRanges() = true

  fun testCallInvocationOperator() { doTest(true); }

  fun testVerbatimCommandArgument() { doTest(true); }

  fun testPathExpression() { doTest(true); }

  fun testCommandCallExpression() { doTest(true); }

  fun testStdCmdlets() { doTest(true); }

  fun testMemberElementAccess() { doTest(true); }

  fun testOperators() { doTest(true); }

  fun testLabeledStatement() { doTest(true); }

  fun testStatements() { doTest(true); }

  fun testFunctionMultilineDefinition() { doTest(true); }

  fun testDesiredSateConfiguration() { doTest(true); }

  fun testArrayStatement() { doTest(true); }

  fun testAttributes() { doTest(true); }

  fun testClosures() { doTest(true); }

  fun testModules() { doTest(true); }

  fun testHashTables() { doTest(true); }

  fun testExpandableString() { doTest(true); }

  fun testVerbatimString() {
    doTest(true);
  }

  fun testTypeLiteral() { doTest(true); }

  fun testClassDeclaration() { doTest(true); }

  fun testEnumDeclaration() { doTest(true); }

  fun testParamBlock() { doTest(true); }

  fun testComment() { doTest(true); }

}
