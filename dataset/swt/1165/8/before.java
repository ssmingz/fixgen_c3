class PlaceHold {
  public void fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((arcWidth == 0) || (arcHeight == 0)) {
      fillRectangle(x, y, width, height);
      return;
    }
    OS.CGContextBeginPath(handle);
    OS.CGContextSaveGState(handle);
    OS.CGContextTranslateCTM(handle, x, y);
    OS.CGContextScaleCTM(handle, arcWidth, arcHeight);
    float fw = width / ((float) (arcWidth));
    float fh = height / ((float) (arcHeight));
    OS.CGContextMoveToPoint(handle, fw, fh / 2);
    OS.CGContextAddArcToPoint(handle, fw, fh, fw / 2, fh, 1);
    OS.CGContextAddArcToPoint(handle, 0, fh, 0, fh / 2, 1);
    OS.CGContextAddArcToPoint(handle, 0, 0, fw / 2, 0, 1);
    OS.CGContextAddArcToPoint(handle, fw, 0, fw, fh / 2, 1);
    OS.CGContextClosePath(handle);
    OS.CGContextRestoreGState(handle);
    OS.CGContextFillPath(handle);
  }
}
