class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, EM_GETLIMITTEXT, 0, 0))) & 0x7fffffff;
  }
}
