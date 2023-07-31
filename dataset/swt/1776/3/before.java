class PlaceHold {
  public void showItem(TableItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int index = indexOf(item);
    if (index != (-1)) {
      OS.SendMessage(handle, LVM_ENSUREVISIBLE, index, 0);
    }
  }
}
