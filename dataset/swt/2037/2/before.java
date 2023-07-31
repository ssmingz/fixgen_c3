class PlaceHold {
  public void testBrowser6() {
    if (isProblematicGtkXulRunner) {
      System.out.println("Test_BrowserSuite.testBrowser1() skipped, see bug 423561");
    }
    assertTrue(Browser6.test());
  }
}
