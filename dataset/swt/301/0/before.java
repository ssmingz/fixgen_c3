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
    int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    if ((style & mask) != 0) {
      if (visible) {
        display.setModalShell(this);
        Control control = display.getFocusControl();
        if ((control != null) && (!control.isActive())) {
          bringToTop();
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
    super.setVisible(visible);
    if (isDisposed()) {
      return;
    }
    if (showWithParent == visible) {
      return;
    }
    showWithParent = visible;
    if (visible) {
      if (!OS.IsWinCE) {
        OS.ShowOwnedPopups(handle, true);
      }
    }
  }
}
