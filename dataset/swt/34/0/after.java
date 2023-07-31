class PlaceHold {
  public void forceActive() {
    checkWidget();
    if (!isVisible()) {
      return;
    }
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    OS.Window_Activate(shellHandle);
  }
}
