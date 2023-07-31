class PlaceHold {
  int convertRgn(int message, int rgn, int r, int newRgn) {
    if (message == OS.kQDRegionToRectsMsgParse) {
      Rect rect = new Rect();
      OS.memmove(rect, r, sizeof);
      CGPoint point = new CGPoint();
      int polyRgn = OS.NewRgn();
      OS.OpenRgn();
      point.x = rect.left;
      point.y = rect.top;
      float[] transform = data.clippingTransform;
      OS.CGPointApplyAffineTransform(point, transform, point);
      short startX;
      short startY;
      OS.MoveTo(startX = ((short) (point.x)), startY = ((short) (point.y)));
      point.x = rect.right;
      point.y = rect.top;
      OS.CGPointApplyAffineTransform(point, transform, point);
      OS.LineTo(((short) (Math.round(point.x))), ((short) (point.y)));
      point.x = rect.right;
      point.y = rect.bottom;
      OS.CGPointApplyAffineTransform(point, transform, point);
      OS.LineTo(((short) (Math.round(point.x))), ((short) (Math.round(point.y))));
      point.x = rect.left;
      point.y = rect.bottom;
      OS.CGPointApplyAffineTransform(point, transform, point);
      OS.LineTo(((short) (point.x)), ((short) (Math.round(point.y))));
      OS.LineTo(startX, startY);
      OS.CloseRgn(polyRgn);
      OS.UnionRgn(newRgn, polyRgn, newRgn);
      OS.DisposeRgn(polyRgn);
    }
    return 0;
  }
}
