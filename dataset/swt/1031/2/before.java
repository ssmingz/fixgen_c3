class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    return OS.SendMessage(handle, LB_GETTOPINDEX, 0, 0);
  }
}
