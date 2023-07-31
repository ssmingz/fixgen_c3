class PlaceHold {
  boolean translateTraversal(MSG msg) {
    int key = msg.wParam;
    if (key == OS.VK_MENU) {
      Shell shell = getShell();
      int hwndShell = shell.handle;
      OS.SendMessage(hwndShell, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
      return false;
    }
    int hwnd = msg.hwnd;
    int detail = SWT.TRAVERSE_NONE;
    boolean doit = true;
    boolean all = false;
    boolean lastVirtual = false;
    int lastKey = key;
    int lastAscii = 0;
    switch (key) {
      case OS.VK_ESCAPE:
        {
          all = true;
          lastAscii = 27;
          int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
          if ((code & OS.DLGC_WANTALLKEYS) != 0) {
            if ((code & OS.DLGC_HASSETSEL) == 0) {
              doit = false;
            }
          }
          detail = SWT.TRAVERSE_ESCAPE;
          break;
        }
      case OS.VK_RETURN:
        {
          all = true;
          lastAscii = '\r';
          int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
          if ((code & OS.DLGC_WANTALLKEYS) != 0) {
            doit = false;
          }
          detail = SWT.TRAVERSE_RETURN;
          break;
        }
      case OS.VK_TAB:
        {
          lastAscii = '\t';
          boolean next = OS.GetKeyState(VK_SHIFT) >= 0;
          int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
          if ((code & (OS.DLGC_WANTTAB | OS.DLGC_WANTALLKEYS)) != 0) {
            if ((code & OS.DLGC_HASSETSEL) != 0) {
              if (next && (OS.GetKeyState(VK_CONTROL) >= 0)) {
                doit = false;
              }
            } else {
              doit = false;
            }
          }
          if ((parent != null) && ((parent.style & SWT.MIRRORED) != 0)) {
            if ((key == OS.VK_LEFT) || (key == OS.VK_RIGHT)) {
              next = !next;
            }
          }
          detail = (next) ? SWT.TRAVERSE_TAB_NEXT : SWT.TRAVERSE_TAB_PREVIOUS;
          break;
        }
      case OS.VK_UP:
      case OS.VK_LEFT:
      case OS.VK_DOWN:
      case OS.VK_RIGHT:
        {
          if (OS.IsSP) {
            if ((key == OS.VK_LEFT) || (key == OS.VK_RIGHT)) {
              return false;
            }
          }
          lastVirtual = true;
          int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
          if ((code & OS.DLGC_WANTARROWS) != 0) {
            doit = false;
          }
          boolean next = (key == OS.VK_DOWN) || (key == OS.VK_RIGHT);
          detail = (next) ? SWT.TRAVERSE_ARROW_NEXT : SWT.TRAVERSE_ARROW_PREVIOUS;
          break;
        }
      case OS.VK_PRIOR:
      case OS.VK_NEXT:
        {
          all = true;
          lastVirtual = true;
          if (OS.GetKeyState(VK_CONTROL) >= 0) {
            return false;
          }
          int code = OS.SendMessage(hwnd, WM_GETDLGCODE, 0, 0);
          if ((code & OS.DLGC_WANTALLKEYS) != 0) {
            if ((code & OS.DLGC_HASSETSEL) == 0) {
              doit = false;
            }
          }
          detail = (key == OS.VK_PRIOR) ? SWT.TRAVERSE_PAGE_PREVIOUS : SWT.TRAVERSE_PAGE_NEXT;
          break;
        }
      default:
        return false;
    }
    Event event = new Event();
    event.doit = doit;
    event.detail = detail;
    display.lastKey = lastKey;
    display.lastAscii = lastAscii;
    display.lastVirtual = lastVirtual;
    display.lastNull = display.lastDead = false;
    if (!setKeyState(event, Traverse, msg.wParam, msg.lParam)) {
      return false;
    }
    Shell shell = getShell();
    Control control = this;
    do {
      if (control.traverse(event)) {
        int hwndShell = shell.handle;
        OS.SendMessage(hwndShell, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
        return true;
      }
      if ((!event.doit) && control.hooks(Traverse)) {
        return false;
      }
      if (control == shell) {
        return false;
      }
      control = control.parent;
    } while (all && (control != null));
    return false;
  }
}
