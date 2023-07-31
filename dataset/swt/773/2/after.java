class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return 0;
    }
    return OS.SendMessage(handle, EM_GETFIRSTVISIBLELINE, 0, 0);
  }
}
