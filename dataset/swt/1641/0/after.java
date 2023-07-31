class PlaceHold {
  int messageProc(int hwnd, int msg, int wParam, int lParam) {
    switch (((int) (msg))) {
      case SWT_RUNASYNC:
        {
          if (runMessagesInIdle) {
            runAsyncMessages(false);
          }
          break;
        }
      case SWT_KEYMSG:
        {
          boolean consumed = false;
          MSG keyMsg = new MSG();
          OS.MoveMemory(keyMsg, lParam, sizeof);
          Control control = findControl(keyMsg.hwnd);
          if (control != null) {
            boolean accentKey = false;
            switch (keyMsg.message) {
              case OS.WM_KEYDOWN:
              case OS.WM_SYSKEYDOWN:
                {
                  if (!OS.IsWinCE) {
                    switch (((int) (keyMsg.wParam))) {
                      case OS.VK_SHIFT:
                      case OS.VK_MENU:
                      case OS.VK_CONTROL:
                      case OS.VK_CAPITAL:
                      case OS.VK_NUMLOCK:
                      case OS.VK_SCROLL:
                        break;
                      default:
                        {
                          int mapKey = OS.MapVirtualKey(((int) (keyMsg.wParam)), 2);
                          if (mapKey != 0) {
                            accentKey = (mapKey & (OS.IsWinNT ? 0x80000000 : 0x8000)) != 0;
                            if (!accentKey) {
                              for (int i = 0; i < Display.ACCENTS.length; i++) {
                                int value = OS.VkKeyScan(ACCENTS[i]);
                                if ((value != (-1)) && ((value & 0xff) == keyMsg.wParam)) {
                                  int state = value >> 8;
                                  if ((((OS.GetKeyState(VK_SHIFT) < 0) == ((state & 0x1) != 0))
                                          && ((OS.GetKeyState(VK_CONTROL) < 0)
                                              == ((state & 0x2) != 0)))
                                      && ((OS.GetKeyState(VK_MENU) < 0) == ((state & 0x4) != 0))) {
                                    if ((state & 0x7) != 0) {
                                      accentKey = true;
                                    }
                                    break;
                                  }
                                }
                              }
                            }
                          }
                          break;
                        }
                    }
                  }
                  break;
                }
            }
            if ((!accentKey) && (!ignoreNextKey)) {
              keyMsg.hwnd = control.handle;
              int flags = ((OS.PM_REMOVE | OS.PM_NOYIELD) | OS.PM_QS_INPUT) | OS.PM_QS_POSTMESSAGE;
              do {
                if (!(consumed |= filterMessage(keyMsg))) {
                  OS.TranslateMessage(keyMsg);
                  consumed |= OS.DispatchMessage(keyMsg) == 1;
                }
              } while (OS.PeekMessage(keyMsg, keyMsg.hwnd, WM_KEYFIRST, WM_KEYLAST, flags));
            }
            switch (keyMsg.message) {
              case OS.WM_KEYDOWN:
              case OS.WM_SYSKEYDOWN:
                {
                  switch (((int) (keyMsg.wParam))) {
                    case OS.VK_SHIFT:
                    case OS.VK_MENU:
                    case OS.VK_CONTROL:
                    case OS.VK_CAPITAL:
                    case OS.VK_NUMLOCK:
                    case OS.VK_SCROLL:
                      break;
                    default:
                      {
                        ignoreNextKey = accentKey;
                        break;
                      }
                  }
                }
            }
          }
          switch (((int) (keyMsg.wParam))) {
            case OS.VK_SHIFT:
            case OS.VK_MENU:
            case OS.VK_CONTROL:
            case OS.VK_CAPITAL:
            case OS.VK_NUMLOCK:
            case OS.VK_SCROLL:
              consumed = true;
          }
          if (consumed) {
            int hHeap = OS.GetProcessHeap();
            OS.HeapFree(hHeap, 0, lParam);
          } else {
            OS.PostMessage(embeddedHwnd, SWT_KEYMSG, wParam, lParam);
          }
          return 0;
        }
      case SWT_TRAYICONMSG:
        {
          if (tray != null) {
            TrayItem[] items = tray.items;
            for (int i = 0; i < items.length; i++) {
              TrayItem item = items[i];
              if ((item != null) && (item.id == wParam)) {
                return item.messageProc(hwnd, ((int) (msg)), wParam, lParam);
              }
            }
          }
          return 0;
        }
      case OS.WM_ACTIVATEAPP:
        {
          if (wParam != 0) {
            if (!isXMouseActive()) {
              int hwndActive = OS.GetActiveWindow();
              if ((hwndActive != 0) && OS.IsWindowEnabled(hwndActive)) {
                break;
              }
              Shell modal = (modalDialog != null) ? modalDialog.parent : getModalShell();
              if ((modal != null) && (!modal.isDisposed())) {
                int hwndModal = modal.handle;
                if (OS.IsWindowEnabled(hwndModal)) {
                  modal.bringToTop();
                  if (modal.isDisposed()) {
                    break;
                  }
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
          }
          break;
        }
      case OS.WM_ENDSESSION:
        {
          if (wParam != 0) {
            dispose();
          }
          break;
        }
      case OS.WM_QUERYENDSESSION:
        {
          Event event = new Event();
          sendEvent(Close, event);
          if (!event.doit) {
            return 0;
          }
          break;
        }
      case OS.WM_DWMCOLORIZATIONCOLORCHANGED:
        {
          sendSettings = true;
        }
      case OS.WM_SETTINGCHANGE:
        {
          if (lastHighContrast != getHighContrast()) {
            sendSettings = true;
            lastHighContrast = getHighContrast();
          }
          if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
            sendSettings = true;
          }
          switch (((int) (wParam))) {
            case 0:
            case 1:
            case OS.SPI_SETHIGHCONTRAST:
              {
                sendSettings = true;
                lastHighContrast = getHighContrast();
              }
          }
          if (sendSettings) {
            OS.SetTimer(hwndMessage, SETTINGS_ID, SETTINGS_DELAY, 0);
          }
          break;
        }
      case OS.WM_THEMECHANGED:
        {
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
          break;
        }
      case OS.WM_TIMER:
        {
          if (wParam == SETTINGS_ID) {
            sendSettings = false;
            OS.KillTimer(hwndMessage, SETTINGS_ID);
            runSettings();
          } else {
            runTimer(wParam);
          }
          break;
        }
      default:
        {
          if (((int) (msg)) == SWT_TASKBARCREATED) {
            if (tray != null) {
              TrayItem[] items = tray.items;
              for (int i = 0; i < items.length; i++) {
                TrayItem item = items[i];
                if (item != null) {
                  item.recreate();
                }
              }
            }
          }
          if (((int) (msg)) == SWT_OPENDOC) {
            String filename = getSharedData(((int) (wParam)), ((int) (lParam)));
            if (filename != null) {
              Event event = new Event();
              event.text = filename;
              sendEvent(OpenDocument, event);
            }
          }
        }
    }
    return OS.DefWindowProc(hwnd, ((int) (msg)), wParam, lParam);
  }
}
