class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if (color == null) {
      return;
    }
    OS.gtk_widget_modify_bg(handle, 0, color.handle);
  }
}
