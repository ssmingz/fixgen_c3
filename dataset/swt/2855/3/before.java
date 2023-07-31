class PlaceHold {
  int windowProc(int hwnd, int msg, int wParam, int lParam) {
    if (msg == OS.EM_UNDO) {
      if ((style & SWT.SINGLE) != 0) {
        LRESULT result = wmClipboard(EM_UNDO, wParam, lParam);
        if (result != null) {
          return result.value;
        }
        return callWindowProc(EM_UNDO, wParam, lParam);
      }
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
