class PlaceHold {
  protected void init() {
    super.init();
    windowCallback = new Callback(this, "windowProc", 4);
    windowProc = windowCallback.getAddress();
    if (windowProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    threadId = OS.GetCurrentThreadId();
    windowClass = new TCHAR(0, WindowName + WindowClassCount, true);
    windowShadowClass = new TCHAR(0, WindowShadowName + WindowClassCount, true);
    WindowClassCount++;
    int hHeap = OS.GetProcessHeap();
    int hInstance = OS.GetModuleHandle(null);
    WNDCLASS lpWndClass = new WNDCLASS();
    lpWndClass.hInstance = hInstance;
    lpWndClass.lpfnWndProc = windowProc;
    lpWndClass.style = OS.CS_BYTEALIGNWINDOW | OS.CS_DBLCLKS;
    lpWndClass.hCursor = OS.LoadCursor(0, IDC_ARROW);
    int byteCount = windowClass.length() * TCHAR.sizeof;
    lpWndClass.lpszClassName = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(lpWndClass.lpszClassName, windowClass, byteCount);
    OS.RegisterClass(lpWndClass);
    OS.HeapFree(hHeap, 0, lpWndClass.lpszClassName);
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(5, 1))) {
      lpWndClass.style |= OS.CS_DROPSHADOW;
    }
    byteCount = windowShadowClass.length() * TCHAR.sizeof;
    lpWndClass.lpszClassName = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(lpWndClass.lpszClassName, windowShadowClass, byteCount);
    OS.RegisterClass(lpWndClass);
    OS.HeapFree(hHeap, 0, lpWndClass.lpszClassName);
    hwndMessage =
        OS.CreateWindowEx(0, windowClass, null, WS_OVERLAPPED, 0, 0, 0, 0, 0, 0, hInstance, null);
    messageCallback = new Callback(this, "messageProc", 4);
    messageProc = messageCallback.getAddress();
    if (messageProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.SetWindowLong(hwndMessage, GWL_WNDPROC, messageProc);
    if (!OS.IsWinCE) {
      msgFilterCallback = new Callback(this, "msgFilterProc", 3);
      msgFilterProc = msgFilterCallback.getAddress();
      if (msgFilterProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      filterHook = OS.SetWindowsHookEx(WH_MSGFILTER, msgFilterProc, 0, threadId);
    }
    if (!OS.IsWinCE) {
      foregroundIdleCallback = new Callback(this, "foregroundIdleProc", 3);
      foregroundIdleProc = foregroundIdleCallback.getAddress();
      if (foregroundIdleProc == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      idleHook = OS.SetWindowsHookEx(WH_FOREGROUNDIDLE, foregroundIdleProc, 0, threadId);
    }
    SWT_TASKBARCREATED = OS.RegisterWindowMessage(new TCHAR(0, "TaskbarCreated", true));
    if (!OS.IsWinCE) {
      OS.OleInitialize(0);
    }
    indexTable = new int[GROW_SIZE];
    controlTable = new Control[GROW_SIZE];
    for (int i = 0; i < (GROW_SIZE - 1); i++) {
      indexTable[i] = i + 1;
    }
    indexTable[GROW_SIZE - 1] = -1;
  }
}
