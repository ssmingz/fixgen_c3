class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if (color == null) {
      return;
    }
    OS.gtk_widget_modify_fg(handle, 0, color.handle);
  }
}
