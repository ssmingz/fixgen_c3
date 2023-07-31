class PlaceHold {
  LRESULT WM_MOVE(int wParam, int lParam) {
    Event event = new Event();
    event.x = ((short) (lParam & 0xffff));
    event.y = ((short) (lParam >> 16));
    sendEvent(Move, event);
    return null;
  }
}
