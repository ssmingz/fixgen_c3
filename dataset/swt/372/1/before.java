class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (image != null) {
      Rectangle rect = image.getBounds();
      return new Rectangle(x, y, rect.width, rect.height);
    }
    return new Rectangle(x, y, width, height);
  }
}
