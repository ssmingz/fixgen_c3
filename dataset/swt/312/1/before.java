class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    return Math.max(0, OS.SendMessage(handle, LVM_GETTOPINDEX, 0, 0));
  }
}
