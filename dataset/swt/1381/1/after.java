class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    GdkColor gdkColor = null;
    if (color != null) {
      if (color.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      gdkColor = color.handle;
    }
    GdkColor oldColor = new GdkColor();
    OS.gtk_style_get_bg(OS.gtk_widget_get_style(fontHandle()), GTK_STATE_NORMAL, oldColor);
    if ((gdkColor == null) || (oldColor.pixel != gdkColor.pixel)) {
      setBackgroundColor(gdkColor);
    }
  }
}
