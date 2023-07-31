class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
      return OS.gtk_status_icon_get_visible(handle);
    }
    return gtk_widget_get_visible(handle);
  }
}
