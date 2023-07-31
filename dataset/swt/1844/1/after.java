class PlaceHold {
  int callWindowProc(int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    if (msg == OS.WM_SYSCHAR) {
      return OS.DefWindowProc(handle, msg, wParam, lParam);
    }
    return OS.CallWindowProc(ToolBarProc, handle, msg, wParam, lParam);
  }
}
