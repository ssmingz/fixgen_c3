class PlaceHold {
  int XButtonPress(int w, int client_data, int call_data, int continue_to_dispatch) {
    Display display = this.display;
    display.hideToolTip();
    Shell shell = getShell();
    if (((shell.style & SWT.ON_TOP) != 0)
        && (((shell.style & SWT.NO_FOCUS) == 0) || ((style & SWT.NO_FOCUS) == 0))) {
      shell.forceActive();
    }
    boolean dispatch = true;
    boolean dragging = false;
    XButtonEvent xEvent = new XButtonEvent();
    OS.memmove(xEvent, call_data, sizeof);
    int clickTime = display.getDoubleClickTime();
    int lastTime = display.lastTime;
    int eventTime = xEvent.time;
    int lastButton = display.lastButton;
    int eventButton = xEvent.button;
    if (((lastButton == eventButton) && (lastTime != 0))
        && (Math.abs(lastTime - eventTime) <= clickTime)) {
      display.clickCount++;
    } else {
      display.clickCount = 1;
    }
    display.lastTime = (eventTime == 0) ? 1 : eventTime;
    display.lastButton = eventButton;
    if (xEvent.button == 2) {
      if (((state & DRAG_DETECT) != 0) && hooks(DragDetect)) {
        boolean[] consume = new boolean[1];
        if (dragDetect(xEvent.x, xEvent.y, true, consume)) {
          dragging = true;
          dispatch = !consume[0];
        }
        if (isDisposed()) {
          return 1;
        }
      }
    }
    if (!sendMouseEvent(MouseDown, xEvent)) {
      dispatch = false;
    }
    if (isDisposed()) {
      return 1;
    }
    if (display.clickCount == 2) {
      if (!sendMouseEvent(MouseDoubleClick, xEvent)) {
        dispatch = false;
      }
      if (isDisposed()) {
        return 1;
      }
    }
    if (dragging) {
      sendDragEvent(xEvent.x, xEvent.y);
      if (isDisposed()) {
        return 1;
      }
    }
    if (xEvent.button == 3) {
      if ((menu != null) || hooks(MenuDetect)) {
        if (!isFocusControl()) {
          setFocus();
        }
      }
      showMenu(xEvent.x_root, xEvent.y_root);
      if (isDisposed()) {
        return 1;
      }
    }
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    if (!dispatch) {
      OS.memmove(continue_to_dispatch, new int[1], 4);
      return 1;
    }
    return 0;
  }
}
