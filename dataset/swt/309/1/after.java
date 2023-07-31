class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    TCHAR buffer = new TCHAR(0, string, true);
    OS.SetWindowText(handle, buffer);
  }
}
