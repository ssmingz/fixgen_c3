class PlaceHold {
  public void drawRectangle(int x, int y, int width, int height) {
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
    CGRect rect = new CGRect();
    rect.x = x + 0.5F;
    rect.y = y + 0.5F;
    rect.width = width;
    rect.height = height;
    OS.CGContextStrokeRect(handle, rect);
    if ((data.control != 0) && (data.paintEvent == 0)) {
      OS.CGContextSynchronize(handle);
    }
  }
}
