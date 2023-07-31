class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.grammars");
    suite.addTest(new TestSuite(GeneratedJava14LexerTest.class));
    suite.addTest(new TestSuite(Post13KeywordsAsIdentifiersOKTest.class));
    suite.addTest(new TestSuite(HexFloatsTest.class));
    suite.addTest(new TestSuite(EmbeddedNullCharTest.class));
    return suite;
  }
}
