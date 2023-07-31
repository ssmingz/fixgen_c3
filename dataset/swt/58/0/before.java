class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLjava_lang_StringII() {
    Font font = new Font(display, "", 10, SWT.NORMAL);
    font.dispose();
    font = new Font(display, "bad-font", 10, SWT.NORMAL);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 0, SWT.NORMAL);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 1000, SWT.NORMAL);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 10, SWT.NORMAL);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 10, SWT.BOLD);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 10, SWT.ITALIC);
    font.dispose();
    font = new Font(display, SwtJunit.testFontName, 10, SWT.BOLD | SWT.ITALIC);
    font.dispose();
    font = new Font(null, SwtJunit.testFontName, 10, SWT.NORMAL);
    font.dispose();
    try {
      font = new Font(display, null, 10, SWT.NORMAL);
      font.dispose();
      fail("No exception thrown for name == null");
    } catch (IllegalArgumentException e) {
    }
    try {
      font = new Font(display, SwtJunit.testFontName, -10, SWT.NORMAL);
      font.dispose();
      fail("No exception thrown for height < 0");
    } catch (IllegalArgumentException e) {
    }
  }
}
