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
    if (from != null) {
      Rect rect = new Rect();
      OS.GetControlBounds(from.handle, rect);
      rectangle.x += rect.left;
      rectangle.y += rect.top;
      int window = OS.GetControlOwner(from.handle);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      rectangle.x += rect.left;
      rectangle.y += rect.top;
    }
    if (to != null) {
      Rect rect = new Rect();
      OS.GetControlBounds(to.handle, rect);
      rectangle.x -= rect.left;
      rectangle.y -= rect.top;
      int window = OS.GetControlOwner(to.handle);
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), rect);
      rectangle.x -= rect.left;
      rectangle.y -= rect.top;
    }
    return rectangle;
  }
}
