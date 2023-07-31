class PlaceHold {
  void setWindowVisible(boolean visible) {
    if (OS.IsWindowVisible(shellHandle) == visible) {
      return;
    }
    if (visible) {
      if (!moved) {
        sendEvent(Move);
        if (isDisposed()) {
          return;
        }
      }
      if (!resized) {
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          layout.layout(this, false);
        }
      }
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      int inModalKind = OS.kWindowModalityNone;
      if ((style & SWT.PRIMARY_MODAL) != 0) {
        inModalKind = OS.kWindowModalityWindowModal;
      }
      if ((style & SWT.APPLICATION_MODAL) != 0) {
        inModalKind = OS.kWindowModalityAppModal;
      }
      if ((style & SWT.SYSTEM_MODAL) != 0) {
        inModalKind = OS.kWindowModalitySystemModal;
      }
      if (inModalKind != OS.kWindowModalityNone) {
        int inUnavailableWindow = 0;
        if (parent != null) {
          inUnavailableWindow = OS.GetControlOwner(handle);
        }
        OS.SetWindowModality(shellHandle, inModalKind, inUnavailableWindow);
      }
      int topHandle = topHandle();
      OS.SetControlVisibility(topHandle, true, false);
      invalidateVisibleRegion(topHandle);
      int[] scope = new int[1];
      if ((style & SWT.ON_TOP) != 0) {
        OS.GetWindowActivationScope(shellHandle, scope);
        OS.SetWindowActivationScope(shellHandle, kWindowActivationScopeNone);
      }
      int shellHandle = this.shellHandle;
      OS.RetainWindow(shellHandle);
      OS.ShowWindow(shellHandle);
      OS.ReleaseWindow(shellHandle);
      if (!isDisposed()) {
        if (minimized != OS.IsWindowCollapsed(shellHandle)) {
          OS.CollapseWindow(shellHandle, minimized);
        }
        if ((style & SWT.ON_TOP) != 0) {
          OS.SetWindowActivationScope(shellHandle, scope[0]);
        }
      }
    } else {
      OS.HideWindow(shellHandle);
      int topHandle = topHandle();
      OS.SetControlVisibility(topHandle, false, false);
      invalidateVisibleRegion(topHandle);
      sendEvent(Hide);
    }
    display.updateQuitMenu();
  }
}
