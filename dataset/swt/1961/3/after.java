class PlaceHold {
  public int getLineCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, EM_GETLINECOUNT, 0, 0)));
  }
}
