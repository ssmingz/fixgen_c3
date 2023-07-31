class PlaceHold {
  public void getClipping(Region region) {
    if (handle == null) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (region == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (region.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    region.subtract(region);
    NSRect rect = null;
    if (data.view != null) {
      rect = data.view.visibleRect();
    } else {
      rect = new NSRect();
      if (data.image != null) {
        NSSize size = data.image.handle.size();
        rect.width = size.width;
        rect.height = size.height;
      } else if (data.size != null) {
        rect.width = data.size.width;
        rect.height = data.size.height;
      }
    }
    region.add(((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
    NSRect paintRect = data.paintRect;
    if (paintRect != null) {
      region.intersect(
          ((int) (paintRect.x)),
          ((int) (paintRect.y)),
          ((int) (paintRect.width)),
          ((int) (paintRect.height)));
    }
    if (data.clipPath != null) {
      NSBezierPath clip = data.clipPath.bezierPathByFlatteningPath();
      int count = clip.elementCount();
      int pointCount = 0;
      Region clipRgn = new Region(device);
      int[] pointArray = new int[count * 2];
      int points = OS.malloc(sizeof);
      if (points == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      NSPoint pt = new NSPoint();
      for (int i = 0; i < count; i++) {
        int element = clip.elementAtIndex_associatedPoints_(i, points);
        switch (element) {
          case OS.NSMoveToBezierPathElement:
            if (pointCount != 0) {
              clipRgn.add(pointArray, pointCount);
            }
            pointCount = 0;
            OS.memmove(pt, points, sizeof);
            pointArray[pointCount++] = ((int) (pt.x));
            pointArray[pointCount++] = ((int) (pt.y));
            break;
          case OS.NSLineToBezierPathElement:
            OS.memmove(pt, points, sizeof);
            pointArray[pointCount++] = ((int) (pt.x));
            pointArray[pointCount++] = ((int) (pt.y));
            break;
          case OS.NSClosePathBezierPathElement:
            if (pointCount != 0) {
              clipRgn.add(pointArray, pointCount);
            }
            pointCount = 0;
            break;
        }
      }
      if (pointCount != 0) {
        clipRgn.add(pointArray, pointCount);
      }
      OS.free(points);
      region.intersect(clipRgn);
      clipRgn.dispose();
    }
    if (data.inverseTransform != null) {
      region.convertRgn(data.inverseTransform);
    }
  }
}
