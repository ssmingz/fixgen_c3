class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if (hwnd == handle) {
      switch (msg) {
        case OS.WM_SIZE:
          {
            ignoreResize = true;
            if ((style & SWT.READ_ONLY) == 0) {
              lockText = true;
            }
            int result = OS.CallWindowProc(ComboProc, hwnd, msg, wParam, lParam);
            if ((style & SWT.READ_ONLY) == 0) {
              lockText = false;
            }
            ignoreResize = false;
            return result;
          }
      }
      return OS.CallWindowProc(ComboProc, hwnd, msg, wParam, lParam);
    }
    int hwndText = OS.GetDlgItem(handle, CBID_EDIT);
    if (hwnd == hwndText) {
      if (lockText && (msg == OS.WM_SETTEXT)) {
        return 0;
      }
      return OS.CallWindowProc(EditProc, hwnd, msg, wParam, lParam);
    }
    int hwndList = OS.GetDlgItem(handle, CBID_LIST);
    if (hwnd == hwndList) {
      return OS.CallWindowProc(ListProc, hwnd, msg, wParam, lParam);
    }
    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
  }
}
