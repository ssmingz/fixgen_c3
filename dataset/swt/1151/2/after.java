class PlaceHold {
  Image getGrayUncheckedImage() {
    if (grayUncheckedImage == null) {
      grayUncheckedImage = new Image(display, GrayUncheckedImageData);
    }
    return grayUncheckedImage;
  }
}
