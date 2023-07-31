class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    int pixel;
    if (color == null) {
      pixel = defaultForeground();
    } else {
      if (color.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      pixel = color.handle;
    }
    setForegroundPixel(pixel);
  }
}
