class PlaceHold {
  public void setBackgroundImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (image == backgroundImage) {
      return;
    }
    backgroundImage = image;
    updateBackgroundImage();
  }
}
