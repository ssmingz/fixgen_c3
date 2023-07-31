class PlaceHold {
  int msgFilterProc(int code, int wParam, int lParam) {
    if (code >= 0) {
      OS.MoveMemory(hookMsg, lParam, sizeof);
      if (hookMsg.message == OS.WM_NULL) {
        runAsyncMessages();
      }
    }
    return OS.CallNextHookEx(hHook, code, wParam, lParam);
  }
}
