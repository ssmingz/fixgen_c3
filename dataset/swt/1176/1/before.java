class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    return OS.SendMessage(handle, EM_GETLIMITTEXT, 0, 0);
  }
}
