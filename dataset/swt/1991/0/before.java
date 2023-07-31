class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    Rect rect = getControlBounds(handle);
    return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
  }
}
