class PlaceHold {
  void releaseDisplay() {
    if (embeddedHwnd != 0) {
      OS.PostMessage(embeddedHwnd, SWT_DESTROY, 0, 0);
    }
    if (OS.COMCTL32_MAJOR >= 6) {
      if (hButtonTheme != 0) {
        OS.CloseThemeData(hButtonTheme);
      }
      if (hEditTheme != 0) {
        OS.CloseThemeData(hEditTheme);
      }
      if (hExplorerBarTheme != 0) {
        OS.CloseThemeData(hExplorerBarTheme);
      }
      if (hScrollBarTheme != 0) {
        OS.CloseThemeData(hScrollBarTheme);
      }
      if (hTabTheme != 0) {
        OS.CloseThemeData(hTabTheme);
      }
      hButtonTheme = hEditTheme = hExplorerBarTheme = hScrollBarTheme = hTabTheme = 0;
    }
    if (!OS.IsWinCE) {
      if (msgHook != 0) {
        OS.UnhookWindowsHookEx(msgHook);
      }
      msgHook = 0;
    }
    if (!OS.IsWinCE) {
      if (filterHook != 0) {
        OS.UnhookWindowsHookEx(filterHook);
      }
      filterHook = 0;
      msgFilterCallback.dispose();
      msgFilterCallback = null;
      msgFilterProc = 0;
    }
    if (!OS.IsWinCE) {
      if (idleHook != 0) {
        OS.UnhookWindowsHookEx(idleHook);
      }
      idleHook = 0;
      foregroundIdleCallback.dispose();
      foregroundIdleCallback = null;
      foregroundIdleProc = 0;
    }
    if (hwndMessage != 0) {
      OS.DestroyWindow(hwndMessage);
    }
    hwndMessage = 0;
    messageCallback.dispose();
    messageCallback = null;
    messageProc = 0;
    int hHeap = OS.GetProcessHeap();
    int hInstance = OS.GetModuleHandle(null);
    OS.UnregisterClass(windowClass, hInstance);
    OS.UnregisterClass(windowShadowClass, hInstance);
    windowClass = windowShadowClass = null;
    windowCallback.dispose();
    windowCallback = null;
    windowProc = 0;
    if (systemFont != null) {
      systemFont.dispose();
    }
    systemFont = null;
    lfSystemFont = null;
    if (errorImage != null) {
      errorImage.dispose();
    }
    if (infoImage != null) {
      infoImage.dispose();
    }
    if (questionImage != null) {
      questionImage.dispose();
    }
    if (warningIcon != null) {
      warningIcon.dispose();
    }
    errorImage = infoImage = questionImage = warningIcon = null;
    if (upArrow != null) {
      upArrow.dispose();
    }
    if (downArrow != null) {
      downArrow.dispose();
    }
    upArrow = downArrow = null;
    for (int i = 0; i < cursors.length; i++) {
      if (cursors[i] != null) {
        cursors[i].dispose();
      }
    }
    cursors = null;
    if (resources != null) {
      for (int i = 0; i < resources.length; i++) {
        if (resources[i] != null) {
          resources[i].dispose();
        }
      }
      resources = null;
    }
    if (lpCustColors != 0) {
      OS.HeapFree(hHeap, 0, lpCustColors);
    }
    lpCustColors = 0;
    if (!OS.IsWinCE) {
      OS.OleUninitialize();
    }
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
      OS.BufferedPaintUnInit();
    }
    thread = null;
    msg = null;
    keyboard = null;
    modalDialogShell = null;
    modalShells = null;
    data = null;
    keys = null;
    values = null;
    bars = popups = null;
    indexTable = null;
    controlTable = null;
    lastControl = lastGetControl = lastHittestControl = null;
    imageList = toolImageList = toolHotImageList = toolDisabledImageList = null;
  }
}
