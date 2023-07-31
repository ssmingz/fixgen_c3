class PlaceHold {
  public Rectangle map(Control from, Control to, int x, int y, int width, int height) {
    checkDevice();
    if ((from != null) && from.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((to != null) && to.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    Rectangle rectangle = new Rectangle(x, y, width, height);
    Rect rect = new Rect();
    if (from != null) {
      int window = OS.GetControlOwner(from.handle);
      CGPoint pt = new CGPoint();
      OS.HIViewConvertPoint(pt, from.handle, 0);
      rectangle.x += ((int) (pt.x));
      rectangle.y += ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      rectangle.x += rect.left;
      rectangle.y += rect.top;
      Rect inset = from.getInset();
      rectangle.x -= inset.left;
      rectangle.y -= inset.top;
    }
    if (to != null) {
      int window = OS.GetControlOwner(to.handle);
      CGPoint pt = new CGPoint();
      OS.HIViewConvertPoint(pt, to.handle, 0);
      rectangle.x -= ((int) (pt.x));
      rectangle.y -= ((int) (pt.y));
      OS.GetWindowBounds(window, ((short) (kWindowStructureRgn)), rect);
      rectangle.x -= rect.left;
      rectangle.y -= rect.top;
      Rect inset = to.getInset();
      rectangle.x += inset.left;
      rectangle.y += inset.top;
    }
    return rectangle;
  }
}
