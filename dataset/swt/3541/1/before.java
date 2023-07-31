class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    int count = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if (!(((0 <= start) && (start <= end)) && (end < count))) {
      error(ERROR_INVALID_RANGE);
    }
    if ((start == 0) && (end == (count - 1))) {
      removeAll();
    } else {
      int index = start;
      while (index <= end) {
        ignoreSelect = true;
        int code = OS.SendMessage(handle, LVM_DELETEITEM, start, 0);
        ignoreSelect = false;
        if (code == 0) {
          break;
        }
        if (items[index] != null) {
          items[index].releaseResources();
        }
        index++;
      }
      System.arraycopy(items, index, items, start, count - index);
      for (int i = count - (index - start); i < count; i++) {
        items[i] = null;
      }
      if (index <= end) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    }
  }
}
