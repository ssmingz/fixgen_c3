class PlaceHold {
  public void drawOval(int x, int y, int width, int height) {
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
    OS.CGContextBeginPath(handle);
    OS.CGContextSaveGState(handle);
    OS.CGContextTranslateCTM(handle, (x + 0.5F) + (width / 2.0F), (y + 0.5F) + (height / 2.0F));
    OS.CGContextScaleCTM(handle, width / 2.0F, height / 2.0F);
    OS.CGContextMoveToPoint(handle, 1, 0);
    OS.CGContextAddArc(handle, 0, 0, 1, 0, ((float) (2 * Math.PI)), true);
    OS.CGContextRestoreGState(handle);
    OS.CGContextStrokePath(handle);
    if ((data.control != 0) && (data.paintEvent == 0)) {
      OS.CGContextSynchronize(handle);
    }
  }
}
