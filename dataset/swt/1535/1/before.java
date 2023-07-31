class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    Shell shell = getShell();
    shell.setToolTipText(handle, toolTipText = string);
  }
}
