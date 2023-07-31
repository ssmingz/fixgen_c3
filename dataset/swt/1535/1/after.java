class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    toolTipText = string;
    setToolTipText(getShell(), string);
  }
}
