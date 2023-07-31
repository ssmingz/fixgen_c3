class PlaceHold {
  public Point getSize() {
    checkWidget();
    Rect rect = getControlSize(topHandle());
    return new Point(rect.right - rect.left, rect.bottom - rect.top);
  }
}
