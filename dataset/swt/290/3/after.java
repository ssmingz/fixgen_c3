class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    boolean redraw = false;
    switch (msg) {
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
        {
          redraw = ((findImageControl() != null) && getDrawing()) && OS.IsWindowVisible(handle);
          if (redraw) {
            OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
          }
          break;
        }
    }
    int code = OS.CallWindowProc(ListProc, hwnd, msg, wParam, lParam);
    switch (msg) {
      case OS.WM_HSCROLL:
      case OS.WM_VSCROLL:
        {
          if (redraw) {
            OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
            OS.InvalidateRect(handle, null, true);
          }
          break;
        }
    }
    return code;
  }
}
