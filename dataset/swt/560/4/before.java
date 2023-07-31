class PlaceHold {
  public void setInsertMark(TreeItem item, boolean before) {
    checkWidget();
    int hItem = 0;
    if (item != null) {
      hItem = item.handle;
    }
    OS.SendMessage(handle, TVM_SETINSERTMARK, before ? 0 : 1, hItem);
  }
}
