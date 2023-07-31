class PlaceHold {
  public Point getSize() {
    checkWidget();
    Rect rect = getControlSize(handle);
    return new Point(rect.right - rect.left, rect.bottom - rect.top);
  }
}
