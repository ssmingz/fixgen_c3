class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.grammars");
    suite.addTest(new TestSuite(GeneratedJava14LexerTest.class));
    return suite;
  }
}
