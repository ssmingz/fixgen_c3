class PlaceHold {
  int gtk_button_press_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.type == OS.GDK_3BUTTON_PRESS) {
      return 0;
    }
    Shell shell = _getShell();
    if (((shell.style & SWT.ON_TOP) != 0)
        && (((shell.style & SWT.NO_FOCUS) == 0) || ((style & SWT.NO_FOCUS) == 0))) {
      shell.forceActive();
    }
    int result = 0;
    if (gdkEvent.type == OS.GDK_BUTTON_PRESS) {
      display.clickCount = 1;
      int nextEvent = OS.gdk_event_peek();
      if (nextEvent != 0) {
        int eventType = OS.GDK_EVENT_TYPE(nextEvent);
        if (eventType == OS.GDK_2BUTTON_PRESS) {
          display.clickCount = 2;
        }
        if (eventType == OS.GDK_3BUTTON_PRESS) {
          display.clickCount = 3;
        }
        OS.gdk_event_free(nextEvent);
      }
      boolean dragging = false;
      if (((state & DRAG_DETECT) != 0) && hooks(DragDetect)) {
        if (gdkEvent.button == 1) {
          boolean[] consume = new boolean[1];
          if (dragDetect(((int) (gdkEvent.x)), ((int) (gdkEvent.y)), true, consume)) {
            dragging = true;
            if (consume[0]) {
              result = 1;
            }
          }
          if (isDisposed()) {
            return 1;
          }
        }
      }
      if (!sendMouseEvent(
          MouseDown,
          gdkEvent.button,
          clickCount,
          0,
          false,
          gdkEvent.time,
          gdkEvent.x_root,
          gdkEvent.y_root,
          false,
          gdkEvent.state)) {
        result = 1;
      }
      if (isDisposed()) {
        return 1;
      }
      if (dragging) {
        sendDragEvent(
            gdkEvent.button, gdkEvent.state, ((int) (gdkEvent.x)), ((int) (gdkEvent.y)), false);
        if (isDisposed()) {
          return 1;
        }
      }
      if ((state & MENU) != 0) {
        if (gdkEvent.button == 3) {
          if (showMenu(((int) (gdkEvent.x_root)), ((int) (gdkEvent.y_root)))) {
            result = 1;
          }
        }
      }
    } else {
      display.clickCount = 2;
      result =
          (sendMouseEvent(
                  MouseDoubleClick,
                  gdkEvent.button,
                  clickCount,
                  0,
                  false,
                  gdkEvent.time,
                  gdkEvent.x_root,
                  gdkEvent.y_root,
                  false,
                  gdkEvent.state))
              ? 0
              : 1;
      if (isDisposed()) {
        return 1;
      }
    }
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    return result;
  }
}
