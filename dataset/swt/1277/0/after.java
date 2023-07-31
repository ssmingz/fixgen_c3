class PlaceHold {
  LRESULT WM_SETFONT(int wParam, int lParam) {
    LRESULT result = super.WM_SETFONT(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (hwndHeader != 0) {
      OS.SendMessage(hwndHeader, WM_SETFONT, wParam, lParam);
    }
    if (itemToolTipHandle != 0) {
      OS.SendMessage(itemToolTipHandle, WM_SETFONT, wParam, lParam);
    }
    if (headerToolTipHandle != 0) {
      OS.SendMessage(headerToolTipHandle, WM_SETFONT, wParam, lParam);
    }
    return result;
  }
}
