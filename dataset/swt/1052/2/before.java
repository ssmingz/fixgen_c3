class PlaceHold {
  public void test_dispose() {
    Font font = new Font(display, SwtJunit.testFontName, 10, SWT.NORMAL);
    assertFalse(font.isDisposed());
    font.dispose();
    assertTrue(font.isDisposed());
  }
}
