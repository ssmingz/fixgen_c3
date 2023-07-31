class PlaceHold {
  LRESULT WM_COMMAND(int wParam, int lParam) {
    if (OS.IsPPC) {
      int loWord = wParam & 0xffff;
      if ((loWord == OS.IDOK) && ((lParam == 0) || (lParam == handle))) {
        OS.PostMessage(handle, WM_CLOSE, 0, 0);
        return LRESULT.ZERO;
      }
    }
    if (OS.IsPPC || OS.IsSP) {
      if (menuBar != null) {
        int hwndCB = menuBar.hwndCB;
        if ((lParam != 0) && (hwndCB != 0)) {
          if (lParam == hwndCB) {
            return super.WM_COMMAND(wParam, 0);
          } else {
            int hwndChild = OS.GetWindow(hwndCB, GW_CHILD);
            if (lParam == hwndChild) {
              return super.WM_COMMAND(wParam, 0);
            }
          }
        }
      }
    }
    return super.WM_COMMAND(wParam, lParam);
  }
}
