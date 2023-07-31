class PlaceHold {
  protected void init() {
    super.init();
    windowCallback = new Callback(this, "windowProc", 4);
    windowProc = windowCallback.getAddress();
    if (windowProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    threadId = OS.GetCurrentThreadId();
    processId = OS.GetCurrentProcessId();
    windowClass = new TCHAR(0, WindowName + (windowClassCount++), true);
    int hHeap = OS.GetProcessHeap();
    int hInstance = OS.GetModuleHandle(null);
    WNDCLASS lpWndClass = new WNDCLASS();
    if (OS.GetClassInfo(hInstance, windowClass, lpWndClass)) {
      OS.UnregisterClass(windowClass, hInstance);
    }
    lpWndClass.hInstance = hInstance;
    lpWndClass.lpfnWndProc = windowProc;
    lpWndClass.style = OS.CS_BYTEALIGNWINDOW | OS.CS_DBLCLKS;
    lpWndClass.hCursor = OS.LoadCursor(0, IDC_ARROW);
    int byteCount = windowClass.length() * TCHAR.sizeof;
    int lpszClassName = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    lpWndClass.lpszClassName = lpszClassName;
    OS.MoveMemory(lpszClassName, windowClass, byteCount);
    OS.RegisterClass(lpWndClass);
    int systemFont = 0;
    if (!OS.IsWinCE) {
      NONCLIENTMETRICS info = new NONCLIENTMETRICS();
      info.cbSize = NONCLIENTMETRICS.sizeof;
      if (OS.SystemParametersInfo(SPI_GETNONCLIENTMETRICS, 0, info, 0)) {
        systemFont = OS.CreateFontIndirect(info.lfMessageFont);
      }
    }
    if (systemFont == 0) {
      systemFont = OS.GetStockObject(DEFAULT_GUI_FONT);
    }
    if (systemFont == 0) {
      systemFont = OS.GetStockObject(SYSTEM_FONT);
    }
    if (systemFont != 0) {
      systemFonts = new int[] {systemFont};
    }
    hwndShell =
        OS.CreateWindowEx(0, windowClass, null, WS_OVERLAPPED, 0, 0, 0, 0, 0, 0, hInstance, null);
    if (!OS.IsWinCE) {
      messageCallback = new Callback(this, "messageProc", 3);
      messageProc = messageCallback.getAddress();
      if (messageProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      hHook = OS.SetWindowsHookEx(WH_MSGFILTER, messageProc, 0, threadId);
    }
  }
}
