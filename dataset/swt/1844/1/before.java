class PlaceHold {
  int callWindowProc(int msg, int wParam, int lParam) {
    if (handle == 0) {
      return 0;
    }
    return OS.CallWindowProc(ToolBarProc, handle, msg, wParam, lParam);
  }
}
