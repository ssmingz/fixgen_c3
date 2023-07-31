class PlaceHold {
  void clearArea(int x, int y, int width, int height) {
    checkWidget();
    if (OS.IsWindowVisible(handle)) {
      RECT rect = new RECT();
      OS.SetRect(rect, x, y, x + width, y + height);
      int hDC = OS.GetDCEx(handle, 0, (OS.DCX_CACHE | OS.DCX_CLIPCHILDREN) | OS.DCX_CLIPSIBLINGS);
      drawBackground(hDC, rect);
      OS.ReleaseDC(handle, hDC);
    }
  }
}
