class PlaceHold {
  public void drawPolygon(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    OS.gdk_draw_polygon(data.drawable, handle, 0, pointArray, pointArray.length / 2);
  }
}
