class PlaceHold {
  public void setBackgroundImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((image == backgroundImage) && (backgroundAlpha > 0)) {
      return;
    }
    backgroundAlpha = 255;
    backgroundImage = image;
    updateBackgroundImage();
  }
}
