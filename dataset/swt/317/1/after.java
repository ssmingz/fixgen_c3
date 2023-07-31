class PlaceHold {
  LRESULT wmSysKeyDown(int hwnd, int wParam, int lParam) {
    int oldSelection = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
    LRESULT result = super.wmSysKeyDown(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((style & SWT.READ_ONLY) == 0) {
      if (wParam == OS.VK_DOWN) {
        int code = callWindowProc(hwnd, WM_SYSKEYDOWN, wParam, lParam);
        int newSelection = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
        if (oldSelection != newSelection) {
          sendEvent(Modify);
          if (isDisposed()) {
            return LRESULT.ZERO;
          }
          sendSelectionEvent(Selection, null, true);
          if (isDisposed()) {
            return LRESULT.ZERO;
          }
        }
        return new LRESULT(code);
      }
    }
    return result;
  }
}
