class PlaceHold {
  void sendTrackEvents() {
    Point outPt = new Point();
    OS.GetGlobalMouse(outPt);
    Rect rect = new Rect();
    int window = OS.GetControlOwner(handle);
    int newX;
    int newY;
    if (OS.HIVIEW) {
      CGPoint pt = new CGPoint();
      pt.x = outPt.h;
      pt.y = outPt.v;
      OS.HIViewConvertPoint(pt, 0, handle);
      newX = ((int) (pt.x));
      newY = ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
    } else {
      OS.GetControlBounds(handle, rect);
      newX = outPt.h - rect.left;
      newY = outPt.v - rect.top;
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
    }
    newX -= rect.left;
    newY -= rect.top;
    int newModifiers = OS.GetCurrentEventKeyModifiers();
    int newState = OS.GetCurrentEventButtonState();
    Display display = this.display;
    int oldX = display.lastX;
    int oldY = display.lastY;
    int oldState = display.lastState;
    int oldModifiers = display.lastModifiers;
    display.lastX = newX;
    display.lastY = newY;
    display.lastModifiers = newModifiers;
    display.lastState = newState;
    boolean events = false;
    if (newState != oldState) {
      int button = 0;
      int type = SWT.MouseDown;
      if (((oldState & 0x1) == 0) && ((newState & 0x1) != 0)) {
        button = 1;
      }
      if (((oldState & 0x2) == 0) && ((newState & 0x2) != 0)) {
        button = 2;
      }
      if (((oldState & 0x4) == 0) && ((newState & 0x4) != 0)) {
        button = 3;
      }
      if (((oldState & 0x8) == 0) && ((newState & 0x8) != 0)) {
        button = 4;
      }
      if (((oldState & 0x10) == 0) && ((newState & 0x10) != 0)) {
        button = 5;
      }
      if (button == 0) {
        type = SWT.MouseUp;
        if (((oldState & 0x1) != 0) && ((newState & 0x1) == 0)) {
          button = 1;
        }
        if (((oldState & 0x2) != 0) && ((newState & 0x2) == 0)) {
          button = 2;
        }
        if (((oldState & 0x4) != 0) && ((newState & 0x4) == 0)) {
          button = 3;
        }
        if (((oldState & 0x8) != 0) && ((newState & 0x8) == 0)) {
          button = 4;
        }
        if (((oldState & 0x10) != 0) && ((newState & 0x10) == 0)) {
          button = 5;
        }
      }
      if (button != 0) {
        sendMouseEvent(
            type,
            ((short) (button)),
            1,
            true,
            newState,
            ((short) (newX)),
            ((short) (newY)),
            newModifiers);
        events = true;
      }
    }
    if ((newModifiers != oldModifiers) && (!isDisposed())) {
      int key = 0;
      int type = SWT.KeyDown;
      if (((newModifiers & OS.alphaLock) != 0) && ((oldModifiers & OS.alphaLock) == 0)) {
        key = SWT.CAPS_LOCK;
      }
      if (((newModifiers & OS.shiftKey) != 0) && ((oldModifiers & OS.shiftKey) == 0)) {
        key = SWT.SHIFT;
      }
      if (((newModifiers & OS.controlKey) != 0) && ((oldModifiers & OS.controlKey) == 0)) {
        key = SWT.CONTROL;
      }
      if (((newModifiers & OS.cmdKey) != 0) && ((oldModifiers & OS.cmdKey) == 0)) {
        key = SWT.COMMAND;
      }
      if (((newModifiers & OS.optionKey) != 0) && ((oldModifiers & OS.optionKey) == 0)) {
        key = SWT.ALT;
      }
      if (key == 0) {
        type = SWT.KeyUp;
        if (((newModifiers & OS.alphaLock) == 0) && ((oldModifiers & OS.alphaLock) != 0)) {
          key = SWT.CAPS_LOCK;
        }
        if (((newModifiers & OS.shiftKey) == 0) && ((oldModifiers & OS.shiftKey) != 0)) {
          key = SWT.SHIFT;
        }
        if (((newModifiers & OS.controlKey) == 0) && ((oldModifiers & OS.controlKey) != 0)) {
          key = SWT.CONTROL;
        }
        if (((newModifiers & OS.cmdKey) == 0) && ((oldModifiers & OS.cmdKey) != 0)) {
          key = SWT.COMMAND;
        }
        if (((newModifiers & OS.optionKey) == 0) && ((oldModifiers & OS.optionKey) != 0)) {
          key = SWT.ALT;
        }
      }
      if (key != 0) {
        Event event = new Event();
        event.keyCode = key;
        setInputState(event, type, newState, newModifiers);
        sendKeyEvent(type, event);
        events = true;
      }
    }
    if ((newX != oldX) || ((newY != oldY) && (!isDisposed()))) {
      if (display.dragging) {
        display.dragging = false;
        sendDragEvent(display.dragX, display.dragY);
        if (isDisposed()) {
          return;
        }
      }
      display.mouseMoved = true;
      sendMouseEvent(
          MouseMove,
          ((short) (0)),
          0,
          true,
          newState,
          ((short) (newX)),
          ((short) (newY)),
          newModifiers);
      events = true;
    }
    if (events) {
      display.runDeferredEvents();
    }
  }
}
