class PlaceHold {
  void createHandle() {
    state |= CANVAS;
    if (window != null) {
    } else {
      window = ((NSWindow) (new NSWindow().alloc()));
      NSRect rect = new NSRect();
      Monitor monitor = getMonitor();
      Rectangle clientArea = monitor.getClientArea();
      rect.width = (clientArea.width * 5) / 8;
      rect.height = (clientArea.height * 5) / 8;
      int styleMask = OS.NSBorderlessWindowMask;
      if ((style & SWT.NO_TRIM) == 0) {
        styleMask = OS.NSTitledWindowMask;
        if ((style & SWT.CLOSE) != 0) {
          styleMask |= OS.NSClosableWindowMask;
        }
        if ((style & SWT.MIN) != 0) {
          styleMask |= OS.NSMiniaturizableWindowMask;
        }
        if ((style & SWT.RESIZE) != 0) {
          styleMask |= OS.NSResizableWindowMask;
        }
      }
      window =
          window.initWithContentRect_styleMask_backing_defer_(
              rect, styleMask, NSBackingStoreBuffered, false);
      display.cascade = window.cascadeTopLeftFromPoint(cascade);
      if ((style & SWT.ON_TOP) != 0) {
        window.setLevel(NSFloatingWindowLevel);
      }
    }
    createHandle(null);
    window.setContentView(view);
    windowDelegate = ((SWTWindowDelegate) (new SWTWindowDelegate().alloc().init()));
    windowDelegate.setTag(jniRef);
    window.setDelegate(windowDelegate);
  }
}
