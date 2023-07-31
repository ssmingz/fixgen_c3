class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (getVisible() == visible) {
      return;
    }
    int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    if ((style & mask) != 0) {
      if (visible) {
        display.setModalShell(this);
      } else {
        display.clearModal(this);
      }
    } else {
      updateModal();
    }
    if (visible) {
      if (center && (!moved)) {
        center();
        if (isDisposed()) {
          return;
        }
      }
      if ((style & SWT.ON_TOP) != 0) {
        OS.Popup_IsOpen(shellHandle, visible);
      } else {
        OS.Window_Show(shellHandle);
      }
      opened = true;
      if (!moved) {
        moved = true;
        Point location = getLocation();
        oldX = location.x;
        oldY = location.y;
        sendEvent(Move);
        if (isDisposed()) {
          return;
        }
      }
      if (!resized) {
        resized = true;
        Point size = getSize();
        oldWidth = size.x;
        oldHeight = size.y;
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          markLayout(false, false);
          updateLayout(false, false);
        }
      }
    } else if (!closing) {
      if ((style & SWT.ON_TOP) != 0) {
        OS.Popup_IsOpen(shellHandle, visible);
      } else {
        OS.Window_Hide(shellHandle);
      }
    }
  }
}
