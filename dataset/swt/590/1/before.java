class PlaceHold {
  int gtk_key_press_event(int widget, int event) {
    int result = super.gtk_key_press_event(widget, event);
    if (result != 0) {
      return result;
    }
    if (OS.GTK_VERSION < OS.VERSION(2, 2, 0)) {
      GdkEventKey keyEvent = new GdkEventKey();
      OS.memmove(keyEvent, event, sizeof);
      int key = keyEvent.keyval;
      switch (key) {
        case OS.GDK_Return:
        case OS.GDK_KP_Enter:
          {
            postEvent(DefaultSelection);
            break;
          }
      }
    }
    return result;
  }
}
