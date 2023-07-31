class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    realizeWidget();
    if (visible) {
      if (center && (!moved)) {
        center();
        if (isDisposed()) {
          return;
        }
      }
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      OS.XtSetMappedWhenManaged(shellHandle, true);
      if (OS.XtIsTopLevelShell(shellHandle)) {
        OS.XtMapWidget(shellHandle);
      } else {
        OS.XtPopup(shellHandle, XtGrabNone);
      }
      boolean iconic = false;
      Shell shell = (parent != null) ? parent.getShell() : null;
      do {
        display.update();
        if (isDisposed()) {
          return;
        }
        iconic = minimized || ((shell != null) && shell.minimized);
      } while ((!isVisible()) && (!iconic));
      if (!iconic) {
        adjustTrim();
      }
      int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.APPLICATION_MODAL;
      if ((style & mask) != 0) {
        OS.XUngrabPointer(xDisplay, CurrentTime);
      }
      opened = true;
      if (!moved) {
        moved = true;
        Point location = getLocation();
        oldX = location.x + trimLeft();
        oldY = location.x + trimTop();
        sendEvent(Move);
        if (isDisposed()) {
          return;
        }
      }
      if (!resized) {
        resized = true;
        Point size = getSize();
        oldWidth = size.x - trimWidth();
        oldHeight = size.y - trimHeight();
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          markLayout(false, false);
          updateLayout(false);
        }
      }
    } else {
      if (parent != null) {
        Shell activeShell = display.getActiveShell();
        if (activeShell == this) {
          Shell shell = parent.getShell();
          shell.bringToTop(false);
        }
      }
      OS.XtSetMappedWhenManaged(shellHandle, false);
      if (OS.XtIsTopLevelShell(shellHandle)) {
        OS.XtUnmapWidget(shellHandle);
      } else {
        OS.XtPopdown(shellHandle);
      }
      int xDisplay = OS.XtDisplay(shellHandle);
      if (xDisplay == 0) {
        return;
      }
      int xWindow = OS.XtWindow(shellHandle);
      if (xWindow == 0) {
        return;
      }
      OS.XWithdrawWindow(xDisplay, xWindow, OS.XDefaultScreen(xDisplay));
      sendEvent(Hide);
    }
  }
}
