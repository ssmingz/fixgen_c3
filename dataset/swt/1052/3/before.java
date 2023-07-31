class PlaceHold {
  public void test_isDisposed() {
    Font font = new Font(display, SwtJunit.testFontName, 10, SWT.NORMAL);
    try {
      assertTrue("Font should not be disposed", !font.isDisposed());
    } finally {
      font.dispose();
      assertTrue("Font should be disposed", font.isDisposed());
    }
  }
}
