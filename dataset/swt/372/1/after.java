class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (image != null) {
      Rectangle rect = image.getBounds();
      return new Rectangle(x, y, rect.width, rect.height);
    } else if (width == 0) {
      return new Rectangle(x, y, DEFAULT_WIDTH, height);
    }
    return new Rectangle(x, y, width, height);
  }
}
