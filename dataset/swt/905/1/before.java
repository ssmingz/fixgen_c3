class PlaceHold {
  TreeItem getFocusItem() {
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
    return hItem != 0 ? _getItem(hItem) : null;
  }
}
