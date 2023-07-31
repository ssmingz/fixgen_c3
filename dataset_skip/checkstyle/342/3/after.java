class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.checks");
    suite.addTest(new TestSuite(ArrayTypeStyleCheckTest.class));
    suite.addTest(new TestSuite(DescendantTokenCheckTest.class));
    suite.addTest(new TestSuite(FileSetCheckLifecycleTest.class));
    suite.addTest(new TestSuite(FinalParametersCheckTest.class));
    suite.addTest(new TestSuite(GenericIllegalRegexpCheckTest.class));
    suite.addTest(new TestSuite(HeaderCheckTest.class));
    suite.addTest(new TestSuite(ModifierOrderCheckTest.class));
    suite.addTest(new TestSuite(NewlineAtEndOfFileCheckTest.class));
    suite.addTest(new TestSuite(RedundantModifierTest.class));
    suite.addTest(new TestSuite(TodoCommentCheckTest.class));
    suite.addTest(new TestSuite(TrailingCommentCheckTest.class));
    suite.addTest(new TestSuite(TranslationCheckTest.class));
    suite.addTest(new TestSuite(UncommentedMainCheckTest.class));
    suite.addTest(new TestSuite(UpperEllCheckTest.class));
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    suite.addTest(AllTests.suite());
    return suite;
  }
}
