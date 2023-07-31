class PlaceHold {
  Image getGrayUncheckedImage() {
    if (grayUncheckedImage == null) {
      grayUncheckedImage = new Image(getDisplay(), GrayUncheckedImageData);
    }
    return grayUncheckedImage;
  }
}
