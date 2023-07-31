class PlaceHold {
  Image getCollapsedImage() {
    if (collapsedImage == null) {
      collapsedImage = new Image(getDisplay(), CollapsedImageData);
    }
    return collapsedImage;
  }
}
