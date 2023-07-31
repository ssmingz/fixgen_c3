class PlaceHold {
  public void fillOval(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    OS.CGContextBeginPath(handle);
    OS.CGContextSaveGState(handle);
    OS.CGContextTranslateCTM(handle, x + (width / 2.0F), y + (height / 2.0F));
    OS.CGContextScaleCTM(handle, width / 2.0F, height / 2.0F);
    OS.CGContextMoveToPoint(handle, 1, 0);
    OS.CGContextAddArc(handle, 0, 0, 1, 0, ((float) (Math.PI * 2)), false);
    OS.CGContextClosePath(handle);
    OS.CGContextRestoreGState(handle);
    OS.CGContextFillPath(handle);
  }
}
