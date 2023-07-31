class PlaceHold {
  Image getUncheckedImage() {
    if (uncheckedImage == null) {
      uncheckedImage = new Image(display, UncheckedImageData);
    }
    return uncheckedImage;
  }
}
