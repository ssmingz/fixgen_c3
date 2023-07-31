class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    GdkColor gdkColor;
    if (color == null) {
      return;
    } else {
      if (color.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      gdkColor = color.handle;
    }
    setForegroundColor(gdkColor);
  }
}
