class PlaceHold {
  LRESULT WM_SYSCOMMAND(int wParam, int lParam) {
    LRESULT result = super.WM_SYSCOMMAND(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.IsWinNT) {
      int cmd = wParam & 0xfff0;
      switch (cmd) {
        case OS.SC_MINIMIZE:
          long memory = Runtime.getRuntime().totalMemory();
          if (memory >= ((32 * 1024) * 1024)) {
            OS.ShowWindow(handle, SW_SHOWMINIMIZED);
            return LRESULT.ZERO;
          }
      }
    }
    return result;
  }
}
