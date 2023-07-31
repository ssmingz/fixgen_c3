class PlaceHold {
  public Rectangle getCloseBounds() {
    checkWidget();
    return new Rectangle(closeRect.x, closeRect.y, closeRect.width, closeRect.height);
  }
}
