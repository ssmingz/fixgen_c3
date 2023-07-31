class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    int pixel;
    if (color == null) {
      pixel = defaultBackground();
    } else {
      if (color.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      pixel = color.handle;
    }
    setBackgroundPixel(pixel);
  }
}
