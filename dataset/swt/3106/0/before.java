class PlaceHold {
  public void showItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    OS.SendMessage(handle, TVM_ENSUREVISIBLE, 0, item.handle);
  }
}
