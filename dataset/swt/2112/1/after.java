class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    if (parent.toolTipText == null) {
      Shell shell = parent._getShell();
      setToolTipText(shell, string, toolTipText);
    }
    toolTipText = string;
  }
}
