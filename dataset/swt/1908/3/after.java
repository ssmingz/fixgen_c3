class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    int count = OS.SendMessage(handle, EM_GETLINECOUNT, 0, 0);
    index = Math.min(Math.max(index, 0), count - 1);
    int topIndex = OS.SendMessage(handle, EM_GETFIRSTVISIBLELINE, 0, 0);
    OS.SendMessage(handle, EM_LINESCROLL, 0, index - topIndex);
  }
}
