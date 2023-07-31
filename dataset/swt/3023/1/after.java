class PlaceHold {
  public Point getSize() {
    checkWidget();
    if (image != null) {
      Rectangle rect = image.getBounds();
      return new Point(rect.width, rect.height);
    } else if (width == 0) {
      new Point(DEFAULT_WIDTH, height);
    }
    return new Point(width, height);
  }
}
