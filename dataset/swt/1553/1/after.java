class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    return Math.max(0, ((int) (OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0))));
  }
}
