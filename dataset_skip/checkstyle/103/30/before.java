class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.api");
    suite.addTest(new TestSuite(AbstractViolationReporterTest.class));
    suite.addTest(new TestSuite(DetailASTTest.class));
    return suite;
  }
}
