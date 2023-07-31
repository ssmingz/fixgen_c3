class PlaceHold {
  public void drawOval(int x, int y, int width, int height) {
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
        OS.FrameOval(fRect.getData());
      }
    } finally {
      unfocus(true);
    }
  }
}
