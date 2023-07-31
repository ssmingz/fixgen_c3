class PlaceHold {
  public void setInsertMark(TreeItem item, boolean before) {
    checkWidget();
    int hItem = 0;
    if (item != null) {
      if (item.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      hItem = item.handle;
    }
    OS.SendMessage(handle, TVM_SETINSERTMARK, before ? 0 : 1, hItem);
  }
}
