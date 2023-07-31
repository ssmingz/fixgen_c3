class PlaceHold {
  public boolean open() {
    checkWidget();
    if (rectangles == null) {
      return false;
    }
    cancelled = false;
    tracking = true;
    window = display.createOverlayWindow();
    OS.ShowWindow(window);
    update();
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
    if (OS.StillDown()) {
      Point pt = new Point();
      OS.GetGlobalMouse(pt);
      cursorPos = new Point(pt.h, pt.v);
    } else if ((style & SWT.RESIZE) != 0) {
      cursorPos = adjustResizeCursor();
    } else {
      cursorPos = adjustMoveCursor();
    }
    oldX = cursorPos.x;
    oldY = cursorPos.y;
    int[] outEvent = new int[1];
    while (tracking && (!cancelled)) {
      int status = OS.ReceiveNextEvent(0, null, kEventDurationNoWait, true, outEvent);
      if (status != OS.noErr) {
        continue;
      }
      int event = outEvent[0];
      int eventClass = OS.GetEventClass(event);
      int eventKind = OS.GetEventKind(event);
      int nextHandler = 0;
      switch (eventClass) {
        case OS.kEventClassMouse:
          switch (eventKind) {
            case OS.kEventMouseUp:
              kEventMouseUp(nextHandler, event, 0);
              break;
            case OS.kEventMouseMoved:
              kEventMouseMoved(nextHandler, event, 0);
              break;
            case OS.kEventMouseDragged:
              kEventMouseDragged(nextHandler, event, 0);
              break;
          }
          break;
        case OS.kEventClassKeyboard:
          switch (eventKind) {
            case OS.kEventRawKeyDown:
              kEventRawKeyDown(nextHandler, event, 0);
              break;
            case OS.kEventRawKeyModifiersChanged:
              kEventRawKeyModifiersChanged(nextHandler, event, 0);
              break;
            case OS.kEventRawKeyRepeat:
              kEventRawKeyRepeat(nextHandler, event, 0);
              break;
            case OS.kEventRawKeyUp:
              kEventRawKeyUp(nextHandler, event, 0);
              break;
          }
          break;
      }
      boolean dispatch = true;
      if (tracking && (!cancelled)) {
        if (eventClass == OS.kEventClassMouse) {
          dispatch = false;
        }
        if (eventClass == OS.kEventClassKeyboard) {
          dispatch = false;
        }
      }
      if (dispatch) {
        OS.SendEventToEventTarget(event, OS.GetEventDispatcherTarget());
      }
      OS.ReleaseEvent(event);
      if ((clientCursor != null) && (resizeCursor == null)) {
        display.setCursor(clientCursor.handle);
      }
    }
    if (!isDisposed()) {
      update();
      drawRectangles(window, rectangles, true);
    }
    OS.DisposeWindow(window);
    tracking = false;
    display.grabControl = null;
    window = 0;
    return !cancelled;
  }
}
