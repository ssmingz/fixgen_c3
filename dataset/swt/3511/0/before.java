class PlaceHold {
  public int indexOf(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int hwnd = parent.handle;
    int hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CHILD, handle);
    return hItem == 0 ? -1 : parent.indexOf(hItem, item.handle);
  }
}
