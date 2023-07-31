class PlaceHold {
  public Rectangle getMinimizeBounds() {
    checkWidget();
    return new Rectangle(minRect.x, minRect.y, minRect.width, minRect.height);
  }
}
