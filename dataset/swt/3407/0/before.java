class PlaceHold {
  public PathData getPathData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int count = handle.elementCount();
    int pointCount = 0;
    int typeCount = 0;
    byte[] types = new byte[count];
    float[] pointArray = new float[count * 6];
    int points = OS.malloc(3 * NSPoint.sizeof);
    if (points == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    NSPoint pt = new NSPoint();
    for (int i = 0; i < count; i++) {
      int element = handle.elementAtIndex_associatedPoints_(i, points);
      switch (element) {
        case OS.NSMoveToBezierPathElement:
          types[typeCount++] = SWT.PATH_MOVE_TO;
          OS.memmove(pt, points, sizeof);
          pointArray[pointCount++] = ((int) (pt.x));
          pointArray[pointCount++] = ((int) (pt.y));
          break;
        case OS.NSLineToBezierPathElement:
          types[typeCount++] = SWT.PATH_LINE_TO;
          OS.memmove(pt, points, sizeof);
          pointArray[pointCount++] = ((int) (pt.x));
          pointArray[pointCount++] = ((int) (pt.y));
          break;
        case OS.NSCurveToBezierPathElement:
          types[typeCount++] = SWT.PATH_CUBIC_TO;
          OS.memmove(pt, points, sizeof);
          pointArray[pointCount++] = ((int) (pt.x));
          pointArray[pointCount++] = ((int) (pt.y));
          OS.memmove(pt, points + NSPoint.sizeof, sizeof);
          pointArray[pointCount++] = ((int) (pt.x));
          pointArray[pointCount++] = ((int) (pt.y));
          OS.memmove(pt, (points + NSPoint.sizeof) + NSPoint.sizeof, sizeof);
          pointArray[pointCount++] = ((int) (pt.x));
          pointArray[pointCount++] = ((int) (pt.y));
          break;
        case OS.NSClosePathBezierPathElement:
          types[typeCount++] = SWT.PATH_CLOSE;
          break;
      }
    }
    OS.free(points);
    if (pointCount != pointArray.length) {
      float[] temp = new float[pointCount];
      System.arraycopy(pointArray, 0, temp, 0, pointCount);
      pointArray = temp;
    }
    PathData data = new PathData();
    data.types = types;
    data.points = pointArray;
    return data;
  }
}
