class PlaceHold {
  public void drawArc(int x, int y, int width, int height, int startAngle, int endAngle) {
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
    if (((width == 0) || (height == 0)) || (endAngle == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    OS.CGContextBeginPath(handle);
    OS.CGContextSaveGState(handle);
    OS.CGContextTranslateCTM(handle, (x + 0.5F) + (width / 2.0F), (y + 0.5F) + (height / 2.0F));
    OS.CGContextScaleCTM(handle, width / 2.0F, height / 2.0F);
    OS.CGContextAddArc(
        handle,
        0,
        0,
        1,
        ((-startAngle) * ((float) (Math.PI))) / 180,
        ((-endAngle) * ((float) (Math.PI))) / 180,
        true);
    OS.CGContextRestoreGState(handle);
    OS.CGContextStrokePath(handle);
  }
}
