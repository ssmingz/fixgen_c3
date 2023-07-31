class PlaceHold {
  public void test_getUrl() {
    shell.setText("test_getUrl");
    String string = browser.getUrl();
    assertTrue(string != null);
  }
}
