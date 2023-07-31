class PlaceHold {
  public Rectangle getMaximizeBounds() {
    checkWidget();
    return new Rectangle(maxRect.x, maxRect.y, maxRect.width, maxRect.height);
  }
}
