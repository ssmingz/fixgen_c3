class PlaceHold {
  public TreeItem getTopItem() {
    checkWidget();
    long hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
    return hItem != 0 ? _getItem(hItem) : null;
  }
}
