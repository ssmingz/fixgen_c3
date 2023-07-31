class PlaceHold {
  public void test_forward() {
    shell.setText("test_forward");
    for (int i = 0; i < 10; i++) {
      browser.forward();
      runLoopTimer(1);
    }
    boolean result = browser.forward();
    assertFalse(result);
  }
}
