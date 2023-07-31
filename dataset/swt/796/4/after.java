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
    TCHAR buffer = new TCHAR(getCodePage(), string, true);
    OS.SetWindowText(handle, buffer);
    if ((style & SWT.MULTI) != 0) {
      sendEvent(Modify);
    }
  }
}
