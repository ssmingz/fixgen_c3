class PlaceHold {
  public String getText() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if (length == 0) {
      return "";
    }
    TCHAR buffer = new TCHAR(getCodePage(), length + 1);
    OS.GetWindowText(handle, buffer, length + 1);
    return buffer.toString(0, length);
  }
}
