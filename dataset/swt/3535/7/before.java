class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmString =
        OS.XmStringParseText(buffer, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, null, 0, 0);
    int[] argList = new int[] {OS.XmNlabelString, xmString};
    OS.XtSetValues(labelHandle, argList, argList.length / 2);
    OS.XmStringFree(xmString);
    if (string.length() == 0) {
      OS.XtUnmanageChild(labelHandle);
    } else {
      OS.XtManageChild(labelHandle);
    }
  }
}
