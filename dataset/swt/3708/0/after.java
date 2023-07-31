class PlaceHold {
  int gtk_button_release_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    int button = gdkEvent.button;
    switch (button) {
      case -6:
        button = 4;
        break;
      case -7:
        button = 5;
        break;
    }
    return sendMouseEvent(
            MouseUp, button, gdkEvent.time, gdkEvent.x_root, gdkEvent.y_root, false, gdkEvent.state)
        ? 0
        : 1;
  }
}
