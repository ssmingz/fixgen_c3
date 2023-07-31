class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for com.puppycrawl.tools.checkstyle.checks.coding");
    suite.addTest(new TestSuite(ArrayTrailingCommaCheckTest.class));
    suite.addTest(new TestSuite(AvoidInlineConditionalsCheckTest.class));
    suite.addTest(new TestSuite(CovariantEqualsCheckTest.class));
    suite.addTest(new TestSuite(DeclarationOrderCheckTest.class));
    suite.addTest(new TestSuite(DefaultComesLastCheckTest.class));
    suite.addTest(new TestSuite(DoubleCheckedLockingCheckTest.class));
    suite.addTest(new TestSuite(EmptyStatementCheckTest.class));
    suite.addTest(new TestSuite(EqualsHashCodeCheckTest.class));
    suite.addTest(new TestSuite(ExplicitInitializationCheckTest.class));
    suite.addTest(new TestSuite(FinalLocalVariableCheckTest.class));
    suite.addTest(new TestSuite(HiddenFieldCheckTest.class));
    suite.addTest(new TestSuite(IllegalCatchCheckTest.class));
    suite.addTest(new TestSuite(IllegalInstantiationCheckTest.class));
    suite.addTest(new TestSuite(IllegalTokenCheckTest.class));
    suite.addTest(new TestSuite(IllegalTokenTextCheckTest.class));
    suite.addTest(new TestSuite(IllegalTypeCheckTest.class));
    suite.addTest(new TestSuite(InnerAssignmentCheckTest.class));
    suite.addTest(new TestSuite(JUnitTestCaseCheckTest.class));
    suite.addTest(new TestSuite(MagicNumberCheckTest.class));
    suite.addTest(new TestSuite(MissingSwitchDefaultCheckTest.class));
    suite.addTest(new TestSuite(NestedIfDepthCheckTest.class));
    suite.addTest(new TestSuite(NestedTryDepthCheckTest.class));
    suite.addTest(new TestSuite(PackageDeclarationCheckTest.class));
    suite.addTest(new TestSuite(ParameterAssignmentCheckTest.class));
    suite.addTest(new TestSuite(RedundantThrowsCheckTest.class));
    suite.addTest(new TestSuite(ReturnCountCheckTest.class));
    suite.addTest(new TestSuite(SimplifyBooleanExpressionCheckTest.class));
    suite.addTest(new TestSuite(SimplifyBooleanReturnCheckTest.class));
    suite.addTest(new TestSuite(StringLiteralEqualityCheckTest.class));
    suite.addTest(new TestSuite(SuperCloneCheckTest.class));
    suite.addTest(new TestSuite(SuperFinalizeCheckTest.class));
    return suite;
  }
}
