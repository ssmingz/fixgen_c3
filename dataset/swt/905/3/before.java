class PlaceHold {
  public void setItemCount(int count) {
    checkWidget();
    count = Math.max(0, count);
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    setItemCount(count, TVGN_ROOT, hItem);
  }
}
