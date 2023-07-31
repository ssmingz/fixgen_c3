class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (drawCount != 0) {
      if (((state & HIDDEN) == 0) == visible) {
        return;
      }
    } else if (visible == OS.IsWindowVisible(handle)) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      if (OS.IsHPC) {
        if (menuBar != null) {
          int hwndCB = menuBar.hwndCB;
          OS.CommandBar_DrawMenuBar(hwndCB, 0);
        }
      }
      if (drawCount != 0) {
        state &= ~HIDDEN;
      } else {
        if (OS.IsWinCE) {
          OS.ShowWindow(handle, SW_SHOW);
        } else {
          if (menuBar != null) {
            display.removeBar(menuBar);
            OS.DrawMenuBar(handle);
          }
          OS.ShowWindow(handle, swFlags);
        }
        if (isDisposed()) {
          return;
        }
        OS.UpdateWindow(handle);
      }
    } else {
      if (!OS.IsWinCE) {
        if (OS.IsIconic(handle)) {
          swFlags = OS.SW_SHOWMINNOACTIVE;
        } else if (OS.IsZoomed(handle)) {
          swFlags = OS.SW_SHOWMAXIMIZED;
        } else if (handle == OS.GetActiveWindow()) {
          swFlags = OS.SW_RESTORE;
        } else {
          swFlags = OS.SW_SHOWNOACTIVATE;
        }
      }
      if (drawCount != 0) {
        state |= HIDDEN;
      } else {
        OS.ShowWindow(handle, SW_HIDE);
      }
      if (isDisposed()) {
        return;
      }
      sendEvent(Hide);
    }
  }
}
