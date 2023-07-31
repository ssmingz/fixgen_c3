class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if ((hwnd == toolTipHandle) || (hwnd == balloonTipHandle)) {
      return OS.CallWindowProc(ToolTipProc, hwnd, msg, wParam, lParam);
    }
    if (hwndMDIClient != 0) {
      return OS.DefFrameProc(hwnd, hwndMDIClient, msg, wParam, lParam);
    }
    if (windowProc != 0) {
      return OS.CallWindowProc(windowProc, hwnd, msg, wParam, lParam);
    }
    if ((style & SWT.TOOL) != 0) {
      int trim = ((((SWT.TITLE | SWT.CLOSE) | SWT.MIN) | SWT.MAX) | SWT.BORDER) | SWT.RESIZE;
      if ((style & trim) == 0) {
        return OS.DefWindowProc(hwnd, msg, wParam, lParam);
      }
    }
    if (parent != null) {
      switch (msg) {
        case OS.WM_KILLFOCUS:
        case OS.WM_SETFOCUS:
          return OS.DefWindowProc(hwnd, msg, wParam, lParam);
      }
      return OS.CallWindowProc(DialogProc, hwnd, msg, wParam, lParam);
    }
    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
  }
}
