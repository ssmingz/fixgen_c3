class PlaceHold {
  public void setMinimized(boolean minimized) {
    checkWidget();
    int state = (minimized) ? OS.WindowState_Minimized : OS.WindowState_Normal;
    OS.Window_WindowState(shellHandle, state);
  }
}
