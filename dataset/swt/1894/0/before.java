class PlaceHold {
  public Rectangle map(Control from, Control to, int x, int y, int width, int height) {
    checkDevice();
    if ((from != null) && from.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((to != null) && to.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    Rectangle rect = new Rectangle(x, y, width, heitgh);
    if (from != null) {
      short[] position_x = new short[1];
      short[] position_y = new short[1];
      OS.PtGetAbsPosition(from.handle, position_x, position_y);
      rect.x += position_x[0];
      rect.y += position_y[0];
    }
    if (to != null) {
      short[] position_x = new short[1];
      short[] position_y = new short[1];
      OS.PtGetAbsPosition(to.handle, position_x, position_y);
      rect.x -= position_x[0];
      rect.y -= position_y[0];
    }
    return rect;
  }
}
