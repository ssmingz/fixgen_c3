class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setImage(image);
    updateImage(true);
  }
}
