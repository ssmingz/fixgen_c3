class PlaceHold {
  public void test_back() {
    for (int i = 0; i < 50; i++) {
      browser.back();
    }
    boolean result = browser.back();
    assertFalse(result);
  }
}
