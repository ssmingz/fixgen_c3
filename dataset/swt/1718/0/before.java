class PlaceHold {
  public void fillRectangle(int x, int y, int width, int height) {
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
        fRect.set(x, y, width, height);
        if ((data.background & 0xff000000) == 0) {
          OS.RGBForeColor(data.background);
          OS.PaintRect(fRect.getData());
        } else {
          short depth = getCurrentScreenDepth();
          int[] state = new int[1];
          OS.GetThemeDrawingState(state);
          if (data.controlHandle != 0) {
            OS.SetUpControlBackground(data.controlHandle, depth, true);
          }
          OS.EraseRect(fRect.getData());
          OS.SetThemeDrawingState(state[0], true);
        }
      }
    } finally {
      unfocus(true);
    }
  }
}
