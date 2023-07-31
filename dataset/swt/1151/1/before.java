class PlaceHold {
  Image getUncheckedImage() {
    if (uncheckedImage == null) {
      uncheckedImage = new Image(getDisplay(), UncheckedImageData);
    }
    return uncheckedImage;
  }
}
