class PlaceHold {
  public boolean open() {
    checkWidget();
    Display display = this.display;
    cancelled = false;
    tracking = true;
    window = ((NSWindow) (new NSWindow().alloc()));
    NSArray screens = NSScreen.screens();
    double minX = Float.MAX_VALUE;
    double maxX = Float.MIN_VALUE;
    double minY = Float.MAX_VALUE;
    double maxY = Float.MIN_VALUE;
    int count = ((int) (screens.count()));
    for (int i = 0; i < count; i++) {
      NSScreen screen = new NSScreen(screens.objectAtIndex(i));
      NSRect frame = screen.frame();
      double x1 = frame.x;
      double x2 = frame.x + frame.width;
      double y1 = frame.y;
      double y2 = frame.y + frame.height;
      if (x1 < minX) {
        minX = x1;
      }
      if (x2 < minX) {
        minX = x2;
      }
      if (x1 > maxX) {
        maxX = x1;
      }
      if (x2 > maxX) {
        maxX = x2;
      }
      if (y1 < minY) {
        minY = y1;
      }
      if (y2 < minY) {
        minY = y2;
      }
      if (y1 > maxY) {
        maxY = y1;
      }
      if (y2 > maxY) {
        maxY = y2;
      }
    }
    NSRect frame = new NSRect();
    frame.x = minX;
    frame.y = minY;
    frame.width = maxX - minX;
    frame.height = maxY - minY;
    window =
        window.initWithContentRect(frame, NSBorderlessWindowMask, NSBackingStoreBuffered, false);
    window.setOpaque(false);
    window.setLevel(NSStatusWindowLevel);
    window.setContentView(null);
    window.setBackgroundColor(NSColor.clearColor());
    NSGraphicsContext context = window.graphicsContext();
    NSGraphicsContext.static_saveGraphicsState();
    NSGraphicsContext.setCurrentContext(context);
    context.setCompositingOperation(NSCompositeClear);
    frame.x = frame.y = 0;
    NSBezierPath.fillRect(frame);
    NSGraphicsContext.static_restoreGraphicsState();
    window.orderFrontRegardless();
    drawRectangles(window, rectangles, false);
    int vStyle = style & (SWT.UP | SWT.DOWN);
    if ((vStyle == SWT.UP) || (vStyle == SWT.DOWN)) {
      cursorOrientation |= vStyle;
    }
    int hStyle = style & (SWT.LEFT | SWT.RIGHT);
    if ((hStyle == SWT.LEFT) || (hStyle == SWT.RIGHT)) {
      cursorOrientation |= hStyle;
    }
    Point cursorPos;
    boolean down = false;
    NSApplication application = NSApplication.sharedApplication();
    NSEvent currentEvent = application.currentEvent();
    if (currentEvent != null) {
      switch (((int) (currentEvent.type()))) {
        case OS.NSLeftMouseDown:
        case OS.NSLeftMouseDragged:
        case OS.NSRightMouseDown:
        case OS.NSRightMouseDragged:
        case OS.NSOtherMouseDown:
        case OS.NSOtherMouseDragged:
          down = true;
      }
    }
    if (down) {
      cursorPos = display.getCursorLocation();
    } else if ((style & SWT.RESIZE) != 0) {
      cursorPos = adjustResizeCursor(true);
    } else {
      cursorPos = adjustMoveCursor();
    }
    if (cursorPos != null) {
      oldX = cursorPos.x;
      oldY = cursorPos.y;
    }
    Control oldTrackingControl = display.trackingControl;
    display.trackingControl = null;
    while (tracking && (!cancelled)) {
      display.addPool();
      try {
        if ((parent != null) && parent.isDisposed()) {
          break;
        }
        display.runSkin();
        display.runDeferredLayouts();
        NSEvent event =
            application.nextEventMatchingMask(
                0, NSDate.distantFuture(), NSDefaultRunLoopMode, true);
        if (event == null) {
          continue;
        }
        int type = ((int) (event.type()));
        switch (type) {
          case OS.NSLeftMouseUp:
          case OS.NSRightMouseUp:
          case OS.NSOtherMouseUp:
          case OS.NSMouseMoved:
          case OS.NSLeftMouseDragged:
          case OS.NSRightMouseDragged:
          case OS.NSOtherMouseDragged:
            mouse(event);
            break;
          case OS.NSKeyDown:
          case OS.NSKeyUp:
          case OS.NSFlagsChanged:
            key(event);
            break;
        }
        boolean dispatch = true;
        switch (type) {
          case OS.NSLeftMouseDown:
          case OS.NSLeftMouseUp:
          case OS.NSRightMouseDown:
          case OS.NSRightMouseUp:
          case OS.NSOtherMouseDown:
          case OS.NSOtherMouseUp:
          case OS.NSMouseMoved:
          case OS.NSLeftMouseDragged:
          case OS.NSRightMouseDragged:
          case OS.NSOtherMouseDragged:
          case OS.NSMouseEntered:
          case OS.NSMouseExited:
          case OS.NSKeyDown:
          case OS.NSKeyUp:
          case OS.NSFlagsChanged:
            dispatch = false;
        }
        if (dispatch) {
          application.sendEvent(event);
        }
        if ((clientCursor != null) && (resizeCursor == null)) {
          display.lockCursor = false;
          clientCursor.handle.set();
          display.lockCursor = true;
        }
        display.runAsyncMessages(false);
      } finally {
        display.removePool();
      }
    }
    if (resizeCursor != null) {
      resizeCursor.dispose();
    }
    resizeCursor = null;
    if ((oldTrackingControl != null) && (!oldTrackingControl.isDisposed())) {
      display.trackingControl = oldTrackingControl;
    }
    display.setCursor(display.findControl(true));
    if (!isDisposed()) {
      drawRectangles(window, rectangles, true);
    }
    if (window != null) {
      window.close();
    }
    tracking = false;
    window = null;
    return !cancelled;
  }
}
