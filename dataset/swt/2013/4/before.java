class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if ((hwnd == toolTipHandle) || (hwnd == balloonTipHandle)) {
      switch (msg) {
        case OS.WM_TIMER:
          {
            if (wParam != ToolTip.TIMER_ID) {
              break;
            }
            ToolTip tip = getCurrentToolTip(hwnd);
            if ((tip != null) && tip.autoHide) {
              tip.setVisible(false);
            }
            break;
          }
        case OS.WM_LBUTTONDOWN:
          {
            ToolTip tip = getCurrentToolTip(hwnd);
            if (tip != null) {
              tip.setVisible(false);
              tip.postEvent(Selection);
            }
            break;
          }
      }
      return callWindowProc(hwnd, msg, wParam, lParam);
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
