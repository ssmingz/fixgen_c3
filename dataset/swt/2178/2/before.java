class PlaceHold {
  public String[] getAvailableTypeNames() {
    int[] count = new int[1];
    int[] max_length = new int[1];
    int xDisplay = OS.XtDisplay(shellHandle);
    if (xDisplay == 0) {
      DND.error(ERROR_UNSPECIFIED);
    }
    int xWindow = OS.XtWindow(shellHandle);
    if (xWindow == 0) {
      DND.error(ERROR_UNSPECIFIED);
    }
    if (OS.XmClipboardInquireCount(xDisplay, xWindow, count, max_length) != OS.XmClipboardSuccess) {
      DND.error(ERROR_UNSPECIFIED);
    }
    String[] types = new String[count[0]];
    for (int i = 0; i < count[0]; i++) {
      byte[] buffer = new byte[max_length[0]];
      int[] copied_length = new int[1];
      int rc =
          OS.XmClipboardInquireFormat(
              xDisplay, xWindow, i + 1, buffer, buffer.length, copied_length);
      if (rc == OS.XmClipboardNoData) {
        types[i] = "";
        continue;
      }
      if (rc != OS.XmClipboardSuccess) {
        DND.error(ERROR_UNSPECIFIED);
      }
      byte[] buffer2 = new byte[copied_length[0]];
      System.arraycopy(buffer, 0, buffer2, 0, copied_length[0]);
      types[i] = new String(buffer2);
    }
    return types;
  }
}
