class PlaceHold {
  boolean sendMouseEvent(
      int type,
      int button,
      int count,
      int detail,
      boolean send,
      int hwnd,
      int msg,
      int wParam,
      int lParam) {
    if ((!hooks(type)) && (!filters(type))) {
      return true;
    }
    Event event = new Event();
    event.button = button;
    event.detail = detail;
    event.count = count;
    event.x = ((short) (lParam & 0xffff));
    event.y = ((short) (lParam >> 16));
    setInputState(event, type);
    mapEvent(hwnd, event);
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
