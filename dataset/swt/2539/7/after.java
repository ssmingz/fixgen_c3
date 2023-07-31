class PlaceHold {
  public void setMaximized(boolean maximized) {
    checkWidget();
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    int state = (maximized) ? OS.WindowState_Maximized : OS.WindowState_Normal;
    OS.Window_WindowState(shellHandle, state);
  }
}
