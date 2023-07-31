class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (!getDrawing()) {
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
          long hwndCB = menuBar.hwndCB;
          OS.CommandBar_DrawMenuBar(hwndCB, 0);
        }
      }
      if (!getDrawing()) {
        state &= ~HIDDEN;
      } else {
        if (OS.IsWinCE) {
          OS.ShowWindow(handle, SW_SHOW);
        } else {
          if (menuBar != null) {
            display.removeBar(menuBar);
            OS.DrawMenuBar(handle);
          }
          STARTUPINFO lpStartUpInfo = Display.lpStartupInfo;
          if ((lpStartUpInfo != null) && ((lpStartUpInfo.dwFlags & OS.STARTF_USESHOWWINDOW) != 0)) {
            OS.ShowWindow(handle, lpStartUpInfo.wShowWindow);
          } else {
            OS.ShowWindow(handle, swFlags);
          }
        }
        if (isDisposed()) {
          return;
        }
        opened = true;
        if (!moved) {
          moved = true;
          Point location = getLocation();
          oldX = location.x;
          oldY = location.y;
        }
        if (!resized) {
          resized = true;
          Rectangle rect = getClientArea();
          oldWidth = rect.width;
          oldHeight = rect.height;
        }
        boolean update = true;
        if (((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) && (!OS.IsAppThemed())) {
          update = !OS.IsHungAppWindow(handle);
        }
        if (update) {
          OS.UpdateWindow(handle);
        }
      }
    } else {
      if (!OS.IsWinCE) {
        if (OS.IsIconic(handle)) {
          swFlags = OS.SW_SHOWMINNOACTIVE;
        } else if (OS.IsZoomed(handle)) {
          swFlags = OS.SW_SHOWMAXIMIZED;
        } else {
          swFlags = OS.SW_SHOWNOACTIVATE;
        }
      }
      if (!getDrawing()) {
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
