class PlaceHold {
  public void drawPolygon(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int cairo = data.cairo;
    if (cairo != 0) {
      drawPolyline(cairo, pointArray, true);
      Cairo.cairo_stroke(cairo);
      return;
    }
    int length = pointArray.length;
    if (length < 4) {
      drawPolyline(pointArray);
      return;
    }
    if ((pointArray[0] == pointArray[length - 2]) && (pointArray[1] == pointArray[length - 1])) {
      drawPolyline(pointArray);
      return;
    }
    int newPoints[] = new int[length + 2];
    for (int i = 0; i < length; i++) {
      newPoints[i] = pointArray[i];
    }
    newPoints[length] = pointArray[0];
    newPoints[length + 1] = pointArray[1];
    drawPolyline(newPoints);
  }
}
