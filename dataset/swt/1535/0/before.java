class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    Shell shell = _getShell();
    shell.setToolTipText(eventHandle(), toolTipText = string);
  }
}
