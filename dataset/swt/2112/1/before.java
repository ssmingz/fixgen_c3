class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    toolTipText = string;
    if (parent.toolTipText == null) {
      Shell shell = parent._getShell();
      setToolTipText(shell, toolTipText);
    }
  }
}
