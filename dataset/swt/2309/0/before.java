class PlaceHold {
  Point adjustMoveCursor() {
    int newX = bounds.x + (bounds.width / 2);
    int newY = bounds.y;
    if (parent != null) {
      Point pt = parent.toDisplay(newX, newY);
      newX = pt.x;
      newY = pt.y;
    }
    CGPoint pt = new CGPoint();
    pt.x = newX;
    pt.y = newY;
    OS.CGWarpMouseCursorPosition(pt);
    return new Point(((int) (pt.x)), ((int) (pt.y)));
  }
}
