class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.image = image;
    isImage = true;
    redraw();
  }
}
