class PlaceHold {
  private int imageWidth() {
    Image image = getImage();
    if ((image != null) && (!image.isDisposed())) {
      return image.getBounds().width;
    } else {
      return 0;
    }
  }
}
