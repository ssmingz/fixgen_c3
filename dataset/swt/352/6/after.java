class PlaceHold {
  void setForegroundColor(GdkColor color) {
    OS.gtk_widget_modify_fg(labelHandle, 0, color);
    OS.gtk_widget_modify_fg(imageHandle, 0, color);
  }
}
