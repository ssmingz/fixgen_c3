class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    this.image = image;
    _setImage(image);
  }
}
