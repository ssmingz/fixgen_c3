class PlaceHold {
  public void fillArc(int x, int y, int width, int height, int startAngle, int endAngle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.updateClip) {
      setCGClipping();
    }
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    if (((width == 0) || (height == 0)) || (endAngle == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    OS.CGContextBeginPath(handle);
    OS.CGContextSaveGState(handle);
    OS.CGContextTranslateCTM(handle, x + (width / 2.0F), y + (height / 2.0F));
    OS.CGContextScaleCTM(handle, width / 2.0F, height / 2.0F);
    OS.CGContextMoveToPoint(handle, 0, 0);
    OS.CGContextAddArc(
        handle,
        0,
        0,
        1,
        ((-startAngle) * ((float) (Math.PI))) / 180,
        ((-endAngle) * ((float) (Math.PI))) / 180,
        true);
    OS.CGContextClosePath(handle);
    OS.CGContextRestoreGState(handle);
    OS.CGContextFillPath(handle);
    if ((data.control != 0) && (data.paintEvent == 0)) {
      OS.CGContextSynchronize(handle);
    }
  }
}
