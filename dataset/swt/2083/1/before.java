class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled) {
      OS.gtk_widget_set_sensitive(handle, true);
    } else {
      OS.GTK_WIDGET_UNSET_FLAGS(handle, GTK_SENSITIVE);
    }
  }
}
