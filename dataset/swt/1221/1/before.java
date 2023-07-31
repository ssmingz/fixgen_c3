class PlaceHold {
  int callWindowProc(int hwnd, int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    return OS.CallWindowProc(TreeProc, hwnd, msg, wParam, lParam);
  }
}
