class PlaceHold {
  public void drawRectangle(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(DRAW);
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    int cairo = data.cairo;
    if (cairo != 0) {
      float offset = ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) ? 0.5F : 0.0F;
      Cairo.cairo_rectangle(cairo, x + offset, y + offset, width, height);
      Cairo.cairo_stroke(cairo);
      return;
    }
    OS.XDrawRectangle(data.display, data.drawable, handle, x, y, width, height);
  }
}
