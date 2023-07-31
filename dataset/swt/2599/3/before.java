class PlaceHold {
  public int getCaretLineNumber() {
    checkWidget();
    return OS.SendMessage(handle, EM_LINEFROMCHAR, -1, 0);
  }
}
