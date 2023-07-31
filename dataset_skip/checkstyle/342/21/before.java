class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.checks.metrics");
    suite.addTest(new TestSuite(BooleanExpressionComplexityCheckTest.class));
    suite.addTest(new TestSuite(ClassFanOutComplexityCheckTest.class));
    suite.addTest(new TestSuite(ClassDataAbstractionCouplingCheckTest.class));
    suite.addTest(new TestSuite(CyclomaticComplexityCheckTest.class));
    suite.addTest(new TestSuite(NPathComplexityCheckTest.class));
    return suite;
  }
}
