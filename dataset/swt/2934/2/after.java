class PlaceHold {
  public void setTransform(Transform transform) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((transform != null) && transform.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((data.cairo == 0) && (transform == null)) {
      return;
    }
    initCairo();
    int cairo = data.cairo;
    Cairo.cairo_concat_matrix(cairo, data.inverseMatrix);
    if (transform != null) {
      Cairo.cairo_concat_matrix(cairo, transform.handle);
      Cairo.cairo_matrix_copy(data.matrix, transform.handle);
      Cairo.cairo_matrix_copy(data.inverseMatrix, transform.handle);
      Cairo.cairo_matrix_invert(data.inverseMatrix);
    } else {
      Cairo.cairo_matrix_set_identity(data.matrix);
      Cairo.cairo_matrix_set_identity(data.inverseMatrix);
    }
    int clipRgn = data.clipRgn;
    if (clipRgn != 0) {
      int matrix = data.inverseMatrix;
      int newRgn = OS.XCreateRegion();
      XRectangle rect = new XRectangle();
      OS.XClipBox(clipRgn, rect);
      short[] pointArray = new short[8];
      double[] x = new double[1];
      double[] y = new double[1];
      x[0] = rect.x;
      y[0] = rect.y;
      Cairo.cairo_matrix_transform_point(matrix, x, y);
      pointArray[0] = ((short) (Math.round(x[0])));
      pointArray[1] = ((short) (Math.round(y[0])));
      x[0] = rect.x + rect.width;
      y[0] = rect.y;
      Cairo.cairo_matrix_transform_point(matrix, x, y);
      pointArray[2] = ((short) (Math.round(x[0])));
      pointArray[3] = ((short) (Math.round(y[0])));
      x[0] = rect.x + rect.width;
      y[0] = rect.y + rect.height;
      Cairo.cairo_matrix_transform_point(matrix, x, y);
      pointArray[4] = ((short) (Math.round(x[0])));
      pointArray[5] = ((short) (Math.round(y[0])));
      x[0] = rect.x;
      y[0] = rect.y + rect.height;
      Cairo.cairo_matrix_transform_point(matrix, x, y);
      pointArray[6] = ((short) (Math.round(x[0])));
      pointArray[7] = ((short) (Math.round(y[0])));
      int polyRgn = OS.XPolygonRegion(pointArray, pointArray.length / 2, EvenOddRule);
      OS.XUnionRegion(handle, polyRgn, handle);
      OS.XDestroyRegion(polyRgn);
      OS.XDestroyRegion(clipRgn);
      data.clipRgn = newRgn;
    }
  }
}
