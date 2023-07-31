class PlaceHold {
  Image getExpandedImage() {
    if (expandedImage == null) {
      expandedImage = new Image(display, ExpandedImageData);
    }
    return expandedImage;
  }
}
