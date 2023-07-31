class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled) {
      OS.gtk_widget_set_sensitive(handle, true);
    } else {
      OS.gtk_widget_set_sensitive(handle, false);
    }
  }
}
