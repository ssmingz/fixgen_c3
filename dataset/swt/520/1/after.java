class PlaceHold {
  public void fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    try {
      if (focus(true, null)) {
        if ((data.background & 0xff000000) == 0) {
          OS.RGBForeColor(data.background);
          fRect.set(x, y, width, height);
          OS.PaintRoundRect(fRect.getData(), ((short) (arcWidth)), ((short) (arcHeight)));
        } else {
        }
      }
    } finally {
      unfocus(true);
    }
  }
}
