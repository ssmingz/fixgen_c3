class PlaceHold {
  public void test_postLorg_eclipse_swt_widgets_Event() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_postLorg_eclipse_swt_widgets_Event(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Display)");
      }
      return;
    }
    final int KEYCODE = SWT.SHIFT;
    Display display = new Display();
    try {
      try {
        display.post(null);
        fail("No exception thrown for post with null argument");
      } catch (IllegalArgumentException e) {
        assertSWTProblem(
            "Incorrect exception thrown for post with null argument", ERROR_NULL_ARGUMENT, e);
      }
      Shell shell = new Shell(display, SWT.NO_TRIM);
      shell.setBounds(display.getBounds());
      shell.open();
      Event event;
      event = new Event();
      event.type = SWT.KeyDown;
      event.keyCode = -1;
      assertTrue(
          "Display#post failed, probably because screen is not rendered (bug 407862)",
          display.post(event));
      event = new Event();
      event.type = SWT.KeyUp;
      assertTrue(display.post(event));
      event.type = SWT.KeyDown;
      event.keyCode = KEYCODE;
      shell.setFocus();
      assertTrue(display.post(event));
      event.type = SWT.KeyUp;
      shell.setFocus();
      assertTrue(display.post(event));
      event = new Event();
      event.type = SWT.MouseMove;
      Rectangle bounds = shell.getBounds();
      event.x = bounds.x + (bounds.width / 2);
      event.y = bounds.y + (bounds.height / 2);
      shell.setFocus();
      assertTrue(display.post(event));
      event = new Event();
      event.type = SWT.MouseDown;
      assertFalse(display.post(event));
      event.button = 1;
      shell.setFocus();
      assertTrue(display.post(event));
      event = new Event();
      event.type = SWT.MouseUp;
      assertFalse(display.post(event));
      event.button = 1;
      shell.setFocus();
      assertTrue(display.post(event));
      event = new Event();
      event.type = SWT.MouseDoubleClick;
      assertFalse(display.post(event));
      shell.dispose();
    } finally {
      display.dispose();
    }
  }
}
