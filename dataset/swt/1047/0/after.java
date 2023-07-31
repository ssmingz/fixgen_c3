class PlaceHold {
  public void test_setUrlLjava_lang_String() {
    try {
      browser.setUrl(null);
      fail("No exception thrown for url == null");
    } catch (IllegalArgumentException e) {
    }
    browser.setUrl("http://www.eclipse.org/swt");
  }
}
