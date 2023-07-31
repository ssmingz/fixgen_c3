class PlaceHold {
  public void test_forward() {
    for (int i = 0; i < 50; i++) {
      browser.forward();
    }
    boolean result = browser.forward();
    assertFalse(result);
  }
}
