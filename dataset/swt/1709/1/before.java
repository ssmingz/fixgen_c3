class PlaceHold {
  int gtk_focus_out_event(int widget, int event) {
    OS.GTK_WIDGET_UNSET_FLAGS(handle, GTK_CAN_FOCUS);
    parent.lastFocus = this;
    return 0;
  }
}
