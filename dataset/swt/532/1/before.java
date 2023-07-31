class PlaceHold {
  boolean sendMouseEvent(
      int type,
      int button,
      int count,
      int detail,
      boolean send,
      int time,
      double x,
      double y,
      boolean is_hint,
      int state) {
    if ((!hooks(type)) && (!filters(type))) {
      return true;
    }
    Event event = new Event();
    event.time = time;
    event.button = button;
    event.detail = detail;
    event.count = count;
    if (is_hint) {
      event.x = ((int) (x));
      event.y = ((int) (y));
    } else {
      int window = eventWindow();
      int[] origin_x = new int[1];
      int[] origin_y = new int[1];
      OS.gdk_window_get_origin(window, origin_x, origin_y);
      event.x = ((int) (x)) - origin_x[0];
      event.y = ((int) (y)) - origin_y[0];
    }
    if ((style & SWT.MIRRORED) != 0) {
      event.x = getClientWidth() - event.x;
    }
    setInputState(event, state);
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
