class PlaceHold {
  public void setSelection(Point selection) {
    checkWidget();
    if (selection == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int[] argList = new int[] {OS.XmNtextField, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    int position = OS.XmTextGetLastPosition(argList[1]);
    if (!OS.XtIsRealized(handle)) {
      getShell().realizeWidget();
    }
    int xDisplay = OS.XtDisplay(argList[1]);
    if (xDisplay == 0) {
      return;
    }
    int nStart = Math.min(Math.max(Math.min(selection.x, selection.y), 0), position);
    int nEnd = Math.min(Math.max(Math.max(selection.x, selection.y), 0), position);
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    OS.XmTextSetSelection(argList[1], nStart, nEnd, OS.XtLastTimestampProcessed(xDisplay));
    OS.XmTextSetInsertionPosition(argList[1], nEnd);
    display.setWarnings(warnings);
  }
}
