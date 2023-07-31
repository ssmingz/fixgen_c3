class PlaceHold {
  int gtk_map_event(int widget, int event) {
    minimized = false;
    sendEvent(Deiconify);
    return 0;
  }
}
