class PlaceHold {
  public Point getSize() {
    checkWidget();
    if (image != null) {
      Rectangle rect = image.getBounds();
      return new Point(rect.width, rect.height);
    }
    return new Point(width, height);
  }
}
