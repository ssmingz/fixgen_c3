class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Framework Tests");
    suite.addTestSuite(TestCaseTest.class);
    suite.addTest(SuiteTest.suite());
    suite.addTestSuite(TestListenerTest.class);
    suite.addTestSuite(AssertionFailedErrorTest.class);
    suite.addTestSuite(AssertTest.class);
    suite.addTestSuite(TestImplementorTest.class);
    suite.addTestSuite(NoArgTestCaseTest.class);
    suite.addTestSuite(ComparisonCompactorTest.class);
    suite.addTestSuite(ComparisonFailureTest.class);
    suite.addTestSuite(DoublePrecisionAssertTest.class);
    suite.addTestSuite(FloatAssertTest.class);
    return suite;
  }
}
