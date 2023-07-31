class PlaceHold {
  public String getSelectionText() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if (length == 0) {
      return "";
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    if (start[0] == end[0]) {
      return "";
    }
    TCHAR buffer = new TCHAR(getCodePage(), length + 1);
    OS.GetWindowText(handle, buffer, length + 1);
    if (segments != null) {
      buffer = deprocessText(buffer, start[0], end[0], false);
      return buffer.toString();
    }
    return buffer.toString(start[0], end[0] - start[0]);
  }
}
