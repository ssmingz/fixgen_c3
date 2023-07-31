class PlaceHold {
  public void drawPolyline(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    float[] points = new float[pointArray.length];
    for (int i = 0; i < points.length; i++) {
      points[i] = pointArray[i];
    }
    OS.CGContextBeginPath(handle);
    OS.CGContextAddLines(handle, points, points.length / 2);
    OS.CGContextStrokePath(handle);
  }
}
