class PlaceHold {
  public void setBackground(Color color) {
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
    setBackgroundColor(gdkColor);
  }
}
