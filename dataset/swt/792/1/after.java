class PlaceHold {
  public void drawPolyline(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int cairo = data.cairo;
    if (cairo != 0) {
      drawPolyline(cairo, pointArray, false);
      Cairo.cairo_stroke(cairo);
      return;
    }
    OS.gdk_draw_lines(data.drawable, handle, pointArray, pointArray.length / 2);
  }
}
