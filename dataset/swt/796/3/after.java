class PlaceHold {
  public void append(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    string = Display.withCrLf(string);
    int length = OS.GetWindowTextLength(handle);
    if (hooks(Verify) || filters(Verify)) {
      string = verifyText(string, length, length, null);
      if (string == null) {
        return;
      }
    }
    OS.SendMessage(handle, EM_SETSEL, length, length);
    TCHAR buffer = new TCHAR(getCodePage(), string, true);
    OS.SendMessage(handle, EM_REPLACESEL, 0, buffer);
    OS.SendMessage(handle, EM_SCROLLCARET, 0, 0);
  }
}
