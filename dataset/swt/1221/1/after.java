class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    switch (msg) {
      case OS.WM_LBUTTONDOWN:
      case OS.WM_MBUTTONDOWN:
      case OS.WM_RBUTTONDOWN:
        display.ignoreMsgFilter = true;
        int code = OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
        display.ignoreMsgFilter = false;
        return code;
    }
    return OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
  }
}
