class PlaceHold {
  public void setMaximized(boolean maximized) {
    checkWidget();
    int state = (maximized) ? OS.WindowState_Maximized : OS.WindowState_Normal;
    OS.Window_WindowState(shellHandle, state);
  }
}
