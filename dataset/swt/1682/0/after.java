class PlaceHold {
  int messageProc(int hwnd, int msg, int wParam, int lParam) {
    switch (msg) {
      case SWT_KEYMSG:
        boolean consumed = false;
        MSG keyMsg = new MSG();
        OS.MoveMemory(keyMsg, lParam, sizeof);
        Control control = findControl(keyMsg.hwnd);
        if (control != null) {
          keyMsg.hwnd = control.handle;
          int flags = OS.PM_REMOVE | OS.PM_NOYIELD;
          do {
            if (!(consumed |= filterMessage(keyMsg))) {
              OS.TranslateMessage(keyMsg);
              consumed |= OS.DispatchMessage(keyMsg) == 1;
            }
          } while (OS.PeekMessage(keyMsg, keyMsg.hwnd, WM_KEYFIRST, WM_KEYLAST, flags));
        }
        if (consumed) {
          int hHeap = OS.GetProcessHeap();
          OS.HeapFree(hHeap, 0, lParam);
        } else {
          OS.PostMessage(embeddedHwnd, SWT_KEYMSG, wParam, lParam);
        }
        return 0;
      case SWT_TRAYICONMSG:
        if (tray != null) {
          TrayItem[] items = tray.items;
          for (int i = 0; i < items.length; i++) {
            TrayItem item = items[i];
            if ((item != null) && (item.id == wParam)) {
              return item.messageProc(hwnd, msg, wParam, lParam);
            }
          }
        }
        return 0;
      case OS.WM_ACTIVATEAPP:
        if (wParam != 0) {
          if ((modalDialogShell != null) && modalDialogShell.isDisposed()) {
            modalDialogShell = null;
          }
          Shell modal = (modalDialogShell != null) ? modalDialogShell : getModalShell();
          if (modal != null) {
            int hwndModal = modal.handle;
            if (OS.IsWindowEnabled(hwndModal)) {
              modal.bringToTop();
            }
            int hwndPopup = OS.GetLastActivePopup(hwndModal);
            if ((hwndPopup != 0) && (hwndPopup != modal.handle)) {
              if (getControl(hwndPopup) == null) {
                if (OS.IsWindowEnabled(hwndPopup)) {
                  OS.SetActiveWindow(hwndPopup);
                }
              }
            }
          }
        }
        break;
      case OS.WM_ENDSESSION:
        if (wParam != 0) {
          dispose();
          System.exit(0);
        }
        break;
      case OS.WM_NULL:
        if (runAsyncMessages()) {
          wakeThread();
        }
        break;
      case OS.WM_QUERYENDSESSION:
        Event event = new Event();
        sendEvent(Close, event);
        if (!event.doit) {
          return 0;
        }
        break;
      case OS.WM_SETTINGCHANGE:
        updateFont();
        break;
      case OS.WM_TIMER:
        runTimer(wParam);
        break;
    }
    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
  }
}
