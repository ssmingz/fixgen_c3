class PlaceHold {
  public void remove(int index) {
    checkWidget();
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if (!((0 <= index) && (index < count))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = _getItem(index, false);
    if ((item != null) && (!item.isDisposed())) {
      item.release(false);
    }
    setDeferResize(true);
    ignoreSelect = ignoreShrink = true;
    int code = OS.SendMessage(handle, LVM_DELETEITEM, index, 0);
    ignoreSelect = ignoreShrink = false;
    if (code == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    _removeItem(index, count);
    --count;
    if (count == 0) {
      setTableEmpty();
    }
    setDeferResize(false);
  }
}
