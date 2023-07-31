class PlaceHold {
  int gtk_button_release_event(int widget, int event) {
    int result = super.gtk_button_release_event(widget, event);
    if (result != 0) {
      return result;
    }
    if (focusIndex == (-1)) {
      return result;
    }
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.button == 1) {
      int x = ((int) (gdkEvent.x));
      int y = ((int) (gdkEvent.y));
      Rectangle[] rects = getRectangles(focusIndex);
      for (int i = 0; i < rects.length; i++) {
        Rectangle rect = rects[i];
        if (rect.contains(x, y)) {
          Event ev = new Event();
          ev.text = ids[focusIndex];
          sendEvent(Selection, ev);
          return result;
        }
      }
    }
    return result;
  }
}
