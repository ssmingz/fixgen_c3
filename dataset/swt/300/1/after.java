class PlaceHold {
  public void setFullScreen(boolean fullScreen) {
    checkWidget();
    if (this.fullScreen == fullScreen) {
      return;
    }
    if (fullScreen) {
      oldWindowStyle = OS.Window_WindowStyle(shellHandle);
      oldWindowState = OS.Window_WindowState(shellHandle);
      OS.Window_Hide(shellHandle);
      OS.Window_WindowStyle(shellHandle, WindowStyle_None);
      if (getVisible()) {
        OS.Window_Show(shellHandle);
      }
      OS.Window_WindowState(shellHandle, WindowState_Maximized);
    } else {
      OS.Window_WindowStyle(shellHandle, oldWindowStyle);
      OS.Window_WindowState(shellHandle, oldWindowState);
      oldWindowState = 0;
      oldWindowStyle = 0;
    }
    this.fullScreen = fullScreen;
  }
}
