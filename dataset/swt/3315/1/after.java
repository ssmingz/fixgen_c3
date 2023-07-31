class PlaceHold {
  public boolean getSelection() {
    checkWidget();
    if ((style & (SWT.CHECK | SWT.RADIO)) == 0) {
      return false;
    }
    char[] outMark = new char[1];
    if (OS.GetMenuCommandMark(parent.handle, id, outMark) != OS.noErr) {
      error(ERROR_CANNOT_GET_SELECTION);
    }
    return outMark[0] != 0;
  }
}
