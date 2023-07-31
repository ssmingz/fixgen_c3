class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if ((hwndParent != 0) && (hwnd == hwndParent)) {
      return OS.DefWindowProc(hwnd, msg, wParam, lParam);
    }
    if ((hwndHeader != 0) && (hwnd == hwndHeader)) {
      return OS.CallWindowProc(HeaderProc, hwnd, msg, wParam, lParam);
    }
    switch (msg) {
      case OS.WM_LBUTTONDOWN:
      case OS.WM_MBUTTONDOWN:
      case OS.WM_RBUTTONDOWN:
      case OS.WM_XBUTTONDOWN:
        {
          Display display = this.display;
          display.ignoreMsgFilter = true;
          int code = OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
          display.ignoreMsgFilter = false;
          return code;
        }
      case OS.WM_MOUSEWHEEL:
        {
          int code = OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
          updateScrollBar();
          return code;
        }
    }
    return OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
  }
}
