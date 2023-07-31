class PlaceHold {
  int gtk_event_after(int widget, int gdkEvent) {
    GdkEvent gtkEvent = new GdkEvent();
    OS.memmove(gtkEvent, gdkEvent, sizeof);
    switch (gtkEvent.type) {
      case OS.GDK_BUTTON_RELEASE:
        {
          GdkEventButton gdkEventButton = new GdkEventButton();
          OS.memmove(gdkEventButton, gdkEvent, sizeof);
          if ((gdkEventButton.button == 1) && (detail == SWT.DRAG)) {
            if (!dragSent) {
              Event event = new Event();
              event.detail = SWT.DRAG;
              postEvent(Selection, event);
            }
            postEvent(Selection);
          }
          detail = OS.GTK_SCROLL_NONE;
          dragSent = false;
          break;
        }
    }
    return super.gtk_event_after(widget, gdkEvent);
  }
}
