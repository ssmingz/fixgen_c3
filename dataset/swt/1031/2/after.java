class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, LB_GETTOPINDEX, 0, 0)));
  }
}
