class PlaceHold {
  public int getOffsetAtLocation(Point point) {
    checkWidget();
    if (point == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int[] trailing = new int[1];
    int offset = getOffsetAtPoint(point.x, point.y, trailing);
    if (offset == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    return offset + trailing[0];
  }
}
