class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int topHandle = topHandle();
    int[] argList = new int[] {OS.XmNmappedWhenManaged, 0};
    OS.XtGetValues(topHandle, argList, argList.length / 2);
    if ((argList[1] != 0) == visible) {
      return;
    }
    boolean fixFocus = false;
    if (!visible) {
      fixFocus = isFocusAncestor();
    }
    OS.XtSetMappedWhenManaged(topHandle, visible);
    if (fixFocus) {
      fixFocus();
    }
    sendEvent(visible ? SWT.Show : SWT.Hide);
  }
}
