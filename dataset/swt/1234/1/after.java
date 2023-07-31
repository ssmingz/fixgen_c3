class PlaceHold {
  @Override
  long gtk_focus_out_event(long widget, long event) {
    OS.gtk_widget_set_can_focus(handle, false);
    parent.lastFocus = this;
    return 0;
  }
}
