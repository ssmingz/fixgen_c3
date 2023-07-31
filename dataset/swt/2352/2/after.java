class PlaceHold {
  public void test_ConstructorLorg_eclipse_swt_graphics_DeviceI() {
    Cursor cursor = new Cursor(display, SWT.CURSOR_ARROW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_WAIT);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_CROSS);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_APPSTARTING);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_HELP);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZEALL);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZENESW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZENS);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZENWSE);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZEWE);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZEN);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZES);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZEE);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZEW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZENE);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZESE);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZESW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_SIZENW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_UPARROW);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_IBEAM);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_NO);
    cursor.dispose();
    cursor = new Cursor(display, SWT.CURSOR_HAND);
    cursor.dispose();
    cursor = new Cursor(null, SWT.CURSOR_ARROW);
    cursor.dispose();
    try {
      cursor = new Cursor(display, 100);
      cursor.dispose();
      fail("No exception thrown for style > SWT.CURSOR_HAND (21)");
    } catch (IllegalArgumentException e) {
    }
    try {
      cursor = new Cursor(display, -100);
      cursor.dispose();
      fail("No exception thrown for style < 0");
    } catch (IllegalArgumentException e) {
    }
    testPerformance(
        new Runnable() {
          public void run() {
            new Cursor(display, SWT.CURSOR_ARROW).dispose();
          }
        });
  }
}
