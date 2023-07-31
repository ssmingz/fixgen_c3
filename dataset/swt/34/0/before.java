class PlaceHold {
  public void forceActive() {
    checkWidget();
    if (!isVisible()) {
      return;
    }
    OS.Window_Activate(shellHandle);
  }
}
