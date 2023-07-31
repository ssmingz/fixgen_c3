class PlaceHold {
  LRESULT WM_LBUTTONDBLCLK(int wParam, int lParam) {
    LRESULT result = null;
    sendMouseEvent(MouseDown, 1, handle, WM_LBUTTONDOWN, wParam, lParam);
    if (!sendMouseEvent(MouseDoubleClick, 1, handle, WM_LBUTTONDBLCLK, wParam, lParam)) {
      result = LRESULT.ZERO;
    }
    if ((!display.captureChanged) && (!isDisposed())) {
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
    }
    if (!doubleClick) {
      return LRESULT.ZERO;
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    if (start[0] == end[0]) {
      int length = OS.GetWindowTextLength(handle);
      if (length == start[0]) {
        int code = ((int) (OS.SendMessage(handle, EM_LINELENGTH, length, 0)));
        if (code == 0) {
          return LRESULT.ZERO;
        }
      }
    }
    return result;
  }
}
