class PlaceHold {
  public void testBrowser1() {
    if (isProblematicGtkXulRunner) {
      System.out.println("Test_BrowserSuite.testBrowser1() skipped, see bug 423561");
      return;
    }
    assertTrue(Browser1.test());
  }
}
