class PlaceHold {
  boolean getListVisible() {
    checkWidget();
    if ((style & SWT.DROP_DOWN) != 0) {
      return OS.SendMessage(handle, CB_GETDROPPEDSTATE, 0, 0) != 0;
    }
    return true;
  }
}
