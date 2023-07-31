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
    setBackgroundColor(gdkColor);
  }
}
