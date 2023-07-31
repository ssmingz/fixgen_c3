class PlaceHold {
  public String getText() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if (length == 0) {
      return "";
    }
    TCHAR buffer = new TCHAR(getCodePage(), length + 1);
    OS.GetWindowText(handle, buffer, length + 1);
    if (segments != null) {
      buffer = deprocessText(buffer, 0, -1, false);
      return buffer.toString();
    }
    return buffer.toString(0, length);
  }
}
