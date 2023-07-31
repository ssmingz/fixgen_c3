class PlaceHold {
  int gtk_focus_out_event(int widget, int event) {
    gtk_widget_set_can_focus(handle, false);
    parent.lastFocus = this;
    return 0;
  }
}
