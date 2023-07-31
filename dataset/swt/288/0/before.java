class PlaceHold {
  public void getCurrentPoint(float[] point) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (point == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (point.length < 2) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    double[] x = new double[1];
    double[] y = new double[1];
    Cairo.cairo_current_point(handle, x, y);
    point[0] = ((float) (x[0]));
    point[1] = ((float) (y[0]));
  }
}
