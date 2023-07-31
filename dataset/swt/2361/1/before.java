class PlaceHold {
  int gtk_button_release_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    int button = gdkEvent.button;
    if (button == (-6)) {
      button = 4;
    }
    if (button == (-7)) {
      button = 5;
    }
    sendMouseEvent(
        MouseUp, button, gdkEvent.time, gdkEvent.x_root, gdkEvent.y_root, gdkEvent.state, event);
    return 0;
  }
}
