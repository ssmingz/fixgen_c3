class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.checks.header");
    suite.addTest(new TestSuite(HeaderCheckTest.class));
    return suite;
  }
}
