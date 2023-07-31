class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    int topHandle = topHandle();
    if (OS.gtk_widget_get_sensitive(topHandle) == enabled) {
      return;
    }
    OS.gtk_widget_set_sensitive(topHandle, enabled);
    if (enabled) {
      int[] x = new int[1];
      int[] y = new int[1];
      OS.gdk_window_get_pointer(parent.paintWindow(), x, y, null);
      if (getBounds().contains(x[0], y[0])) {
        OS.gtk_widget_hide(handle);
        OS.gtk_widget_show(handle);
      }
    } else if (OS.GTK_VERSION >= OS.VERSION(2, 14, 0)) {
      OS.gtk_widget_set_state(topHandle, GTK_STATE_NORMAL);
    }
  }
}
