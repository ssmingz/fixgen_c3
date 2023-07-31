class PlaceHold {
  public void setMinimized(boolean minimized) {
    checkWidget();
    if (this.minimized == minimized) {
      return;
    }
    super.setMinimized(minimized);
    if ((!minimized) && OS.IsWindowCollapsed(shellHandle)) {
      OS.SelectWindow(shellHandle);
    }
    OS.CollapseWindow(shellHandle, minimized);
  }
}
