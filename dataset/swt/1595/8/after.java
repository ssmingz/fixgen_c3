class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int topHandle = topHandle();
    int[] argList = new int[] {OS.XmNmappedWhenManaged, 0};
    OS.XtGetValues(topHandle, argList, argList.length / 2);
    if ((argList[1] != 0) == visible) {
      return;
    }
    Control control = null;
    boolean fixFocus = false;
    if (!visible) {
      if (display.focusEvent != SWT.FocusOut) {
        control = display.getFocusControl();
        fixFocus = isFocusAncestor(control);
      }
    }
    OS.XtSetMappedWhenManaged(topHandle, visible);
    if (fixFocus) {
      fixFocus(control);
    }
    if (isDisposed()) {
      return;
    }
    sendEvent(visible ? SWT.Show : SWT.Hide);
  }
}
