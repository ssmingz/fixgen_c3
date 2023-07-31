class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    string = Display.withCrLf(string);
    if (hooks(Verify) || filters(Verify)) {
      int length = OS.GetWindowTextLength(handle);
      string = verifyText(string, 0, length, null);
      if (string == null) {
        return;
      }
    }
    int limit = OS.SendMessage(handle, EM_GETLIMITTEXT, 0, 0) & 0x7fffffff;
    if (string.length() > limit) {
      string = string.substring(0, limit);
    }
    TCHAR buffer = new TCHAR(getCodePage(), string, true);
    OS.SetWindowText(handle, buffer);
    if ((style & SWT.MULTI) != 0) {
      sendEvent(Modify);
    }
  }
}
