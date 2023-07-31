class PlaceHold {
  public void test_hashCode() {
    Font font = new Font(display, SwtJunit.testFontName, 10, SWT.NORMAL);
    assertEquals(font, font);
    assertEquals(font.hashCode(), font.hashCode());
    final Font boldFont = new Font(display, SwtJunit.testFontName, 10, SWT.BOLD);
    assertFalse(font.hashCode() == boldFont.hashCode());
    testPerformance(
        new Runnable() {
          public void run() {
            boldFont.hashCode();
          }
        });
    boldFont.dispose();
    font.dispose();
  }
}
