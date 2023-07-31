class PlaceHold {
  public char[] getTextChars() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if (length == 0) {
      return new char[0];
    }
    TCHAR buffer = new TCHAR(getCodePage(), length + 1);
    OS.GetWindowText(handle, buffer, length + 1);
    if (segments != null) {
      buffer = deprocessText(buffer, 0, -1, false);
    }
    char[] chars = new char[length];
    System.arraycopy(buffer.chars, 0, chars, 0, length);
    return chars;
  }
}
