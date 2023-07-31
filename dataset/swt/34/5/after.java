class PlaceHold {
  public void setMinimized(boolean minimized) {
    checkWidget();
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    int state = (minimized) ? OS.WindowState_Minimized : OS.WindowState_Normal;
    OS.Window_WindowState(shellHandle, state);
  }
}
