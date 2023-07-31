class PlaceHold {
  public int getItemCount() {
    checkWidget();
    long hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    if (hItem == 0) {
      return 0;
    }
    return getItemCount(hItem);
  }
}
