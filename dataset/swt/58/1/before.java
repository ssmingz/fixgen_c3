class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceLorg_eclipse_swt_graphics_FontData() {
    Font font = new Font(display, new FontData("", 10, SWT.NORMAL));
    font.dispose();
    font = new Font(display, new FontData("bad-font", 10, SWT.NORMAL));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 0, SWT.NORMAL));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 1000, SWT.NORMAL));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 10, SWT.NORMAL));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 10, SWT.BOLD));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 10, SWT.ITALIC));
    font.dispose();
    font = new Font(display, new FontData(SwtJunit.testFontName, 10, SWT.BOLD | SWT.ITALIC));
    font.dispose();
    try {
      font = new Font(display, ((FontData) (null)));
      font.dispose();
      fail("No exception thrown for fontData == null");
    } catch (IllegalArgumentException e) {
    }
    try {
      font = new Font(display, new FontData(null, 10, SWT.NORMAL));
      font.dispose();
      fail("No exception thrown for name == null");
    } catch (IllegalArgumentException e) {
    }
    try {
      font = new Font(display, new FontData(SwtJunit.testFontName, -10, SWT.NORMAL));
      font.dispose();
      fail("No exception thrown for height < 0");
    } catch (IllegalArgumentException e) {
    }
  }
}
