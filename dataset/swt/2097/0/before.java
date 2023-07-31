class PlaceHold {
  public void select(int index) {
    checkWidget();
    if (index == (-1)) {
      int[] argList = new int[] {OS.XmNtextField, 0, OS.XmNlist, 0};
      OS.XtGetValues(handle, argList, argList.length / 2);
      Display display = getDisplay();
      boolean warnings = display.getWarnings();
      display.setWarnings(false);
      OS.XmTextSetString(argList[1], new byte[1]);
      OS.XmTextSetInsertionPosition(argList[1], 0);
      display.setWarnings(warnings);
      OS.XmListDeselectAllItems(argList[3]);
    } else {
      int[] argList = new int[] {OS.XmNitemCount, 0};
      OS.XtGetValues(handle, argList, argList.length / 2);
      if (!((0 <= index) && (index < argList[1]))) {
        error(ERROR_INVALID_RANGE);
      }
      int[] argList2 = new int[] {OS.XmNselectedPosition, index};
      OS.XtSetValues(handle, argList2, argList2.length / 2);
    }
  }
}
