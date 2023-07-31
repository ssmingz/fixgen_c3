class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    if ((style & mask) != 0) {
      if (visible) {
        display.setModalShell(this);
        if ((style & (SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL)) != 0) {
          display.setModalDialog(null);
        }
        Control control = display._getFocusControl();
        if ((control != null) && (!control.isActive())) {
          bringToTop();
          if (isDisposed()) {
            return;
          }
        }
        int hwndShell = OS.GetActiveWindow();
        if (hwndShell == 0) {
          if (parent != null) {
            hwndShell = parent.handle;
          }
        }
        if (hwndShell != 0) {
          OS.SendMessage(hwndShell, WM_CANCELMODE, 0, 0);
        }
        OS.ReleaseCapture();
      } else {
        display.clearModal(this);
      }
    } else {
      updateModal();
    }
    if (showWithParent && (!visible)) {
      if (!OS.IsWinCE) {
        OS.ShowOwnedPopups(handle, false);
      }
    }
    if (!visible) {
      fixActiveShell();
    }
    if ((visible && center) && (!moved)) {
      center();
      if (isDisposed()) {
        return;
      }
    }
    super.setVisible(visible);
    if (isDisposed()) {
      return;
    }
    if (showWithParent != visible) {
      showWithParent = visible;
      if (visible) {
        if (!OS.IsWinCE) {
          OS.ShowOwnedPopups(handle, true);
        }
      }
    }
    if (visible) {
      if ((parent != null) && ((parent.state & FOREIGN_HANDLE) != 0)) {
        int hwndParent = parent.handle;
        int style = OS.GetWindowLong(hwndParent, GWL_EXSTYLE);
        if ((style & OS.WS_EX_TOOLWINDOW) != 0) {
          OS.SetWindowLong(hwndParent, GWL_EXSTYLE, style & (~OS.WS_EX_TOOLWINDOW));
          OS.ShowWindow(hwndParent, SW_HIDE);
          OS.ShowWindow(hwndParent, SW_RESTORE);
        }
      }
    }
  }
}
