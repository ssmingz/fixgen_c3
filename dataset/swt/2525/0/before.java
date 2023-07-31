class PlaceHold {
  Image getExpandedImage() {
    if (expandedImage == null) {
      expandedImage = new Image(getDisplay(), ExpandedImageData);
    }
    return expandedImage;
  }
}
