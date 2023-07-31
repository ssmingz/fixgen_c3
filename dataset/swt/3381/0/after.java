class PlaceHold {
  void appendBezierPath(NSBezierPath path) {
    int count = ((int) (path.elementCount()));
    long points = OS.malloc(3 * NSPoint.sizeof);
    if (points == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    NSPoint pt1 = new NSPoint();
    NSPoint pt2 = new NSPoint();
    NSPoint pt3 = new NSPoint();
    for (int i = 0; i < count; i++) {
      int element = ((int) (path.elementAtIndex(i, points)));
      switch (element) {
        case OS.NSMoveToBezierPathElement:
          OS.memmove(pt1, points, sizeof);
          if (closed) {
            handle.moveToPoint(pt1);
          } else {
            handle.lineToPoint(pt1);
          }
          break;
        case OS.NSLineToBezierPathElement:
          OS.memmove(pt1, points, sizeof);
          handle.lineToPoint(pt1);
          closed = false;
          break;
        case OS.NSCurveToBezierPathElement:
          OS.memmove(pt1, points, sizeof);
          OS.memmove(pt2, points + NSPoint.sizeof, sizeof);
          OS.memmove(pt3, (points + NSPoint.sizeof) + NSPoint.sizeof, sizeof);
          handle.curveToPoint(pt3, pt1, pt2);
          closed = false;
          break;
        case OS.NSClosePathBezierPathElement:
          handle.closePath();
          closed = true;
          break;
      }
    }
    OS.free(points);
  }
}
