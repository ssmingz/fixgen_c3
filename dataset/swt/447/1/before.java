class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled) {
      OS.GTK_WIDGET_SET_FLAGS(handle, GTK_SENSITIVE);
    } else {
      OS.GTK_WIDGET_UNSET_FLAGS(handle, GTK_SENSITIVE);
    }
  }
}
