class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.checks.javadoc");
    suite.addTest(new TestSuite(JavadocMethodCheckTest.class));
    suite.addTest(new TestSuite(JavadocStyleCheckTest.class));
    suite.addTest(new TestSuite(JavadocTypeCheckTest.class));
    suite.addTest(new TestSuite(JavadocVariableCheckTest.class));
    suite.addTest(new TestSuite(PackageHtmlCheckTest.class));
    suite.addTest(new TestSuite(WriteTagCheckTest.class));
    return suite;
  }
}
