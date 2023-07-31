class PlaceHold {
  public int indexOf(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    return hItem == 0 ? -1 : findIndex(hItem, item.handle);
  }
}
