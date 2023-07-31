class PlaceHold {
  public void test_back() {
    shell.setText("test_back");
    for (int i = 0; i < 10; i++) {
      browser.back();
      runLoopTimer(1);
    }
    boolean result = browser.back();
    assertFalse(result);
  }
}
