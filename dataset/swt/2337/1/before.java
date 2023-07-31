class PlaceHold {
  private int imageHeight() {
    Image image = getImage();
    if (parent.getImageHeight() != (-1)) {
      return parent.getImageHeight();
    } else if (image != null) {
      return image.getBounds().height;
    } else {
      return 0;
    }
  }
}
