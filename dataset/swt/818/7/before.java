class PlaceHold {
  int gtk_key_press_event(int widget, int eventPtr) {
    int result = super.gtk_key_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    if (focusIndex == (-1)) {
      return result;
    }
    GdkEventKey gdkEvent = new GdkEventKey();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    switch (gdkEvent.keyval) {
      case OS.GDK_Return:
      case OS.GDK_KP_Enter:
      case OS.GDK_space:
        Event event = new Event();
        event.text = ids[focusIndex];
        sendEvent(Selection, event);
        break;
      case OS.GDK_Tab:
        if (focusIndex < (offsets.length - 1)) {
          focusIndex++;
          redraw();
        }
        break;
      case OS.GDK_ISO_Left_Tab:
        if (focusIndex > 0) {
          focusIndex--;
          redraw();
        }
        break;
    }
    return result;
  }
}
