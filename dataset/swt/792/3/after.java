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
    short[] xPoints = new short[pointArray.length];
    for (int i = 0; i < pointArray.length; i++) {
      xPoints[i] = ((short) (pointArray[i]));
    }
    OS.XDrawLines(
        data.display, data.drawable, handle, xPoints, xPoints.length / 2, CoordModeOrigin);
  }
}
