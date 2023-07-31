class PlaceHold {
  int gtk_button_press_event(int widget, int eventPtr) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, eventPtr, sizeof);
    if (gdkEvent.type == OS.GDK_3BUTTON_PRESS) {
      return 0;
    }
    if ((gdkEvent.button == 3) && (gdkEvent.type == OS.GDK_BUTTON_PRESS)) {
      sendEvent(MenuDetect);
      return 0;
    }
    if (gdkEvent.type == OS.GDK_2BUTTON_PRESS) {
      sendSelectionEvent(DefaultSelection);
    } else {
      sendSelectionEvent(Selection);
    }
    return 0;
  }
}
