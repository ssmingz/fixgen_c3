class PlaceHold {
  public void test_dispose() {
    Font font = new Font(display, SwtTestUtil.testFontName, 10, SWT.NORMAL);
    assertFalse(font.isDisposed());
    font.dispose();
    assertTrue(font.isDisposed());
  }
}
