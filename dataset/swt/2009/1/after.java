class PlaceHold {
  public static void drawGlyphs(GC gc, char[] renderBuffer, int[] renderDx, int x, int y) {
    int length = renderDx.length;
    if ((!OS.IsWinCE) && (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10))) {
      if (OS.GetLayout(gc.handle) != 0) {
        reverse(renderDx);
        renderDx[length - 1]--;
        reverse(renderBuffer);
      }
    }
    int oldBkMode = OS.SetBkMode(gc.handle, TRANSPARENT);
    OS.ExtTextOutW(
        gc.handle, x, y, ETO_GLYPH_INDEX, null, renderBuffer, renderBuffer.length, renderDx);
    OS.SetBkMode(gc.handle, oldBkMode);
  }
}
