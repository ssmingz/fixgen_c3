class PlaceHold {
  public void subtract(int[] pointArray) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int polyRgn = OS.CreatePolygonRgn(pointArray, pointArray.length / 2, ALTERNATE);
    OS.CombineRgn(handle, handle, polyRgn, RGN_DIFF);
    OS.DeleteObject(polyRgn);
  }
}
