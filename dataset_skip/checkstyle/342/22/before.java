class PlaceHold {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.puppycrawl.tools.checkstyle.checks.whitespace");
    suite.addTest(new TestSuite(EmptyForIteratorPadCheckTest.class));
    suite.addTest(new TestSuite(NoWhitespaceAfterCheckTest.class));
    suite.addTest(new TestSuite(NoWhitespaceBeforeCheckTest.class));
    suite.addTest(new TestSuite(OperatorWrapCheckTest.class));
    suite.addTest(new TestSuite(ParenPadCheckTest.class));
    suite.addTest(new TestSuite(TabCharacterCheckTest.class));
    suite.addTest(new TestSuite(TypecastParenPadCheckTest.class));
    suite.addTest(new TestSuite(WhitespaceAfterCheckTest.class));
    suite.addTest(new TestSuite(WhitespaceAroundTest.class));
    return suite;
  }
}
