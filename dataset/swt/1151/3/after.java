class PlaceHold {
  Image getCollapsedImage() {
    if (collapsedImage == null) {
      collapsedImage = new Image(display, CollapsedImageData);
    }
    return collapsedImage;
  }
}
