class PlaceHold {
  public void test_toString() {
    Font font = new Font(display, SwtTestUtil.testFontName, 10, SWT.NORMAL);
    assertNotNull(font.toString());
    font.dispose();
    assertNotNull(font.toString());
  }
}
