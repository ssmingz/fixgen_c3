class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    boolean fixPaint = findBackgroundControl() != null;
    if (!fixPaint) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        Control control = findThemeControl();
        fixPaint = control != null;
      }
    }
    if (fixPaint) {
      boolean redraw = (drawCount == 0) && OS.IsWindowVisible(handle);
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
      }
      ignoreResize = true;
      OS.SendMessage(handle, WM_SIZE, 0, 0);
      ignoreResize = false;
      if (redraw) {
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        OS.InvalidateRect(handle, null, false);
      }
    }
    return super.WM_PAINT(wParam, lParam);
  }
}
