class PlaceHold {
  boolean sendMouseEvent(
      int type,
      short button,
      int count,
      int detail,
      boolean send,
      int chord,
      short x,
      short y,
      int modifiers) {
    if ((!hooks(type)) && (!filters(type))) {
      return true;
    }
    if ((state & SAFARI_EVENTS_FIX) != 0) {
      switch (type) {
        case SWT.MouseUp:
        case SWT.MouseMove:
        case SWT.MouseDoubleClick:
          {
            return true;
          }
        case SWT.MouseDown:
          {
            if (button == 1) {
              return true;
            }
            break;
          }
      }
    }
    Event event = new Event();
    switch (button) {
      case 1:
        event.button = 1;
        break;
      case 2:
        event.button = 3;
        break;
      case 3:
        event.button = 2;
        break;
      case 4:
        event.button = 4;
        break;
      case 5:
        event.button = 5;
        break;
    }
    event.x = x;
    event.y = y;
    event.count = count;
    event.detail = detail;
    setInputState(event, type, chord, modifiers);
    if (send) {
      sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
    } else {
      postEvent(type, event);
    }
    return event.doit;
  }
}
