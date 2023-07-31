class PlaceHold {
  public void drawRectangle(int x, int y, int width, int height) {
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
    CGRect rect = new CGRect();
    rect.x = x + 0.5F;
    rect.y = y + 0.5F;
    rect.width = width;
    rect.height = height;
    OS.CGContextStrokeRect(handle, rect);
  }
}
