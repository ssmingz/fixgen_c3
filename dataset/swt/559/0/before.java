class PlaceHold {
  public void setBackgroundImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (image == backgroundImage) {
      return;
    }
    this.backgroundImage = image;
    if (backgroundImage != null) {
      setBackgroundPixmap(backgroundImage);
      redrawWidget(0, 0, 0, 0, true, false, false);
    } else {
      setWidgetBackground();
    }
    redrawChildren();
  }
}
