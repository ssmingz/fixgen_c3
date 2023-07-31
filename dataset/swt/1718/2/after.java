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
    try {
      if (focus(true, null)) {
        if ((data.background & 0xff000000) == 0) {
          MacUtil.RGBForeColor(data.background);
          fRect.set(x, y, width, height);
          OS.PaintOval(fRect.getData());
        } else {
        }
      }
    } finally {
      unfocus(true);
    }
  }
}
