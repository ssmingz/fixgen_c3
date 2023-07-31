class PlaceHold {
  public void setLineWidth(int width) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    data.lineWidth = width;
    OS.CGContextSetLineWidth(handle, Math.max(1, width));
  }
}
