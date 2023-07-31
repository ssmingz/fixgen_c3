class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (OS.gtk_widget_get_sensitive(handle) == enabled) {
      return;
    }
    int accelGroup = getAccelGroup();
    if (accelGroup != 0) {
      removeAccelerator(accelGroup);
    }
    OS.gtk_widget_set_sensitive(handle, enabled);
    if (accelGroup != 0) {
      addAccelerator(accelGroup);
    }
  }
}
