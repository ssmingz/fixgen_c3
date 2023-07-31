class PlaceHold {
  public Point map(Control from, Control to, int x, int y) {
    checkDevice();
    if ((from != null) && from.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((to != null) && to.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    Point point = new Point(x, y);
    Rect rect = new Rect();
    if (from != null) {
      int window = OS.GetControlOwner(from.handle);
      CGPoint pt = new CGPoint();
      OS.HIViewConvertPoint(pt, from.handle, 0);
      point.x += ((int) (pt.x));
      point.y += ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      point.x += rect.left;
      point.y += rect.top;
      Rect inset = from.getInset();
      point.x -= inset.left;
      point.y -= inset.top;
    }
    if (to != null) {
      int window = OS.GetControlOwner(to.handle);
      CGPoint pt = new CGPoint();
      OS.HIViewConvertPoint(pt, to.handle, 0);
      point.x -= ((int) (pt.x));
      point.y -= ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      point.x -= rect.left;
      point.y -= rect.top;
      Rect inset = to.getInset();
      point.x += inset.left;
      point.y += inset.top;
    }
    return point;
  }
}
