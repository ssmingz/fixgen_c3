class PlaceHold {
  public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
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
        MacUtil.RGBForeColor(data.foreground);
        OS.PenSize(((short) (fLineWidth)), ((short) (fLineWidth)));
        fRect.set(x, y, width + 1, height + 1);
        OS.FrameRoundRect(fRect.getData(), ((short) (arcWidth)), ((short) (arcHeight)));
      }
    } finally {
      unfocus(true);
    }
  }
}
