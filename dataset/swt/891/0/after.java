class PlaceHold {
  int regionToRects(int message, int rgn, int r, int context) {
    if (message == OS.kQDRegionToRectsMsgParse) {
      Rect rect = new Rect();
      OS.memmove(rect, r, sizeof);
      OS.CGContextMoveToPoint(context, rect.left, rect.top);
      OS.CGContextAddLineToPoint(context, rect.right, rect.top);
      OS.CGContextAddLineToPoint(context, rect.right, rect.bottom);
      OS.CGContextAddLineToPoint(context, rect.left, rect.bottom);
      OS.CGContextClosePath(context);
    }
    return 0;
  }
}
