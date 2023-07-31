class PlaceHold {
  public void setImage(Image image) {
    super.setImage(image);
    int oldImageHeight = imageHeight;
    if (image != null) {
      Rectangle bounds = image.getBounds();
      imageHeight = bounds.height;
      imageWidth = bounds.width;
    } else {
      imageHeight = imageWidth = 0;
    }
    if (oldImageHeight != imageHeight) {
      parent.layoutItems(parent.indexOf(this), true);
    } else {
      redraw();
    }
  }
}
