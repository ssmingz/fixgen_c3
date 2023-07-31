class PlaceHold {
  int gtk_key_press_event(int widget, int eventPtr) {
    int result = super.gtk_key_press_event(widget, eventPtr);
    if (result != 0) {
      return result;
    }
    if (OS.GTK_VERSION < OS.VERSION(2, 2, 0)) {
      GdkEventKey keyEvent = new GdkEventKey();
      OS.memmove(keyEvent, eventPtr, sizeof);
      int key = keyEvent.keyval;
      switch (key) {
        case OS.GDK_Return:
        case OS.GDK_KP_Enter:
          {
            Event event = new Event();
            event.item = getFocusItem();
            postEvent(DefaultSelection, event);
            break;
          }
      }
    }
    return result;
  }
}
