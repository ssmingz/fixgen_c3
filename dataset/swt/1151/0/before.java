class PlaceHold {
  Image getCheckMarkImage() {
    if (checkMarkImage == null) {
      checkMarkImage = new Image(getDisplay(), CheckMarkImageData);
    }
    return checkMarkImage;
  }
}
