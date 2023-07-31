class PlaceHold {
  public boolean getSelection() {
    checkWidget();
    if ((style & ((SWT.CHECK | SWT.RADIO) | SWT.TOGGLE)) == 0) {
      return false;
    }
    int flags = OS.SendMessage(handle, BM_GETCHECK, 0, 0);
    return flags != OS.BST_UNCHECKED;
  }
}
