class PlaceHold {
  public void setHotImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    hotImage = image;
    if ((parent.style & SWT.FLAT) != 0) {
      redraw();
    }
  }
}
