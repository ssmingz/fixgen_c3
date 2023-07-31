class PlaceHold {
  public void open() {
    checkWidget();
    OS.SelectWindow(shellHandle);
    setVisible(true);
    if (!restoreFocus()) {
      traverseGroup(true);
    }
  }
}
