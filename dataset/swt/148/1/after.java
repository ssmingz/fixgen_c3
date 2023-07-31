class PlaceHold {
  LRESULT WM_PARENTNOTIFY(int wParam, int lParam) {
    LRESULT result = super.WM_PARENTNOTIFY(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
      return result;
    }
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      int code = OS.LOWORD(wParam);
      switch (code) {
        case OS.WM_CREATE:
          {
            int id = OS.HIWORD(wParam);
            int hwnd = lParam;
            if (id == ID_UPDOWN) {
              int bits = OS.GetWindowLong(hwnd, GWL_EXSTYLE);
              OS.SetWindowLong(hwnd, GWL_EXSTYLE, bits | OS.WS_EX_LAYOUTRTL);
            }
            break;
          }
      }
    }
    return result;
  }
}
