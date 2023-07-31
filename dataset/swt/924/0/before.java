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
    try {
      if (focus(true, null)) {
        MacUtil.RGBForeColor(data.foreground);
        OS.PenSize(((short) (data.lineWidth)), ((short) (data.lineWidth)));
        Rect rect = new Rect();
        rect.left = ((short) (x));
        rect.top = ((short) (y));
        rect.right = ((short) ((x + width) + 1));
        rect.bottom = ((short) ((y + height) + 1));
        OS.FrameRect(rect);
      }
    } finally {
      unfocus(true);
    }
  }
}
