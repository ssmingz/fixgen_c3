class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if (!(((0 <= start) && (start <= end)) && (end < count))) {
      error(ERROR_INVALID_RANGE);
    }
    if ((start == 0) && (end == (count - 1))) {
      removeAll();
    } else {
      setDeferResize(true);
      int index = start;
      while (index <= end) {
        TableItem item = _getItem(index, false);
        if ((item != null) && (!item.isDisposed())) {
          item.release(false);
        }
        ignoreSelect = ignoreShrink = true;
        int code = OS.SendMessage(handle, LVM_DELETEITEM, start, 0);
        ignoreSelect = ignoreShrink = false;
        if (code == 0) {
          break;
        }
        index++;
      }
      _removeItems(start, index, count);
      if (index <= end) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
      setDeferResize(false);
    }
  }
}
