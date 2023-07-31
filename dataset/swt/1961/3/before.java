class PlaceHold {
  public int getLineCount() {
    checkWidget();
    return OS.SendMessage(handle, EM_GETLINECOUNT, 0, 0);
  }
}
