class PlaceHold {
  boolean setFocus() {
    if (!OS.gtk_widget_get_child_visible(handle)) {
      return false;
    }
    gtk_widget_set_can_focus(handle, true);
    OS.gtk_widget_grab_focus(handle);
    if (isDisposed()) {
      return false;
    }
    boolean result = OS.gtk_widget_is_focus(handle);
    if (!result) {
      OS.GTK_WIDGET_UNSET_FLAGS(handle, GTK_CAN_FOCUS);
    }
    return result;
  }
}
