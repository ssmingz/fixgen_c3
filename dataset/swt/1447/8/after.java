class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.image = image;
  }
}
