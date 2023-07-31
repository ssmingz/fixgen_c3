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
      int newRgn = OS.gdk_region_new();
      int[] nRects = new int[1];
      int[] rects = new int[1];
      OS.gdk_region_get_rectangles(clipRgn, rects, nRects);
      GdkRectangle rect = new GdkRectangle();
      int[] pointArray = new int[8];
      double[] x = new double[1];
      double[] y = new double[1];
      for (int i = 0; i < nRects[0]; i++) {
        OS.memmove(rect, rects[0] + (i * GdkRectangle.sizeof), sizeof);
        x[0] = rect.x;
        y[0] = rect.y;
        Cairo.cairo_matrix_transform_point(matrix, x, y);
        pointArray[0] = ((int) (Math.round(x[0])));
        pointArray[1] = ((int) (Math.round(y[0])));
        x[0] = rect.x + rect.width;
        y[0] = rect.y;
        Cairo.cairo_matrix_transform_point(matrix, x, y);
        pointArray[2] = ((int) (Math.round(x[0])));
        pointArray[3] = ((int) (Math.round(y[0])));
        x[0] = rect.x + rect.width;
        y[0] = rect.y + rect.height;
        Cairo.cairo_matrix_transform_point(matrix, x, y);
        pointArray[4] = ((int) (Math.round(x[0])));
        pointArray[5] = ((int) (Math.round(y[0])));
        x[0] = rect.x;
        y[0] = rect.y + rect.height;
        Cairo.cairo_matrix_transform_point(matrix, x, y);
        pointArray[6] = ((int) (Math.round(x[0])));
        pointArray[7] = ((int) (Math.round(y[0])));
        int polyRgn = OS.gdk_region_polygon(pointArray, pointArray.length / 2, GDK_EVEN_ODD_RULE);
        OS.gdk_region_union(newRgn, polyRgn);
        OS.gdk_region_destroy(polyRgn);
      }
      OS.gdk_region_destroy(clipRgn);
      data.clipRgn = newRgn;
    }
  }
}
