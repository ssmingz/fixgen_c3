class PlaceHold {
  int gtk_button_press_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    double x = gdkEvent.x;
    gdkEvent.x += OS.GTK_WIDGET_X(handle);
    double y = gdkEvent.y;
    gdkEvent.y += OS.GTK_WIDGET_Y(handle);
    OS.memmove(event, gdkEvent, sizeof);
    parent.gtk_button_press_event(widget, event);
    gdkEvent.x = x;
    gdkEvent.y = y;
    OS.memmove(event, gdkEvent, sizeof);
    return 0;
  }
}
