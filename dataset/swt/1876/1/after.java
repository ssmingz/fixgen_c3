class PlaceHold {
  public void fillPolygon(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int nullPen = OS.GetStockObject(NULL_PEN);
    int oldPen = OS.SelectObject(handle, nullPen);
    OS.Polygon(handle, pointArray, pointArray.length / 2);
    OS.SelectObject(handle, oldPen);
  }
}
