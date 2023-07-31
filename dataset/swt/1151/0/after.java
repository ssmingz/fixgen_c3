class PlaceHold {
  Image getCheckMarkImage() {
    if (checkMarkImage == null) {
      checkMarkImage = new Image(display, CheckMarkImageData);
    }
    return checkMarkImage;
  }
}
