class PlaceHold {
  public void setItemCount(int count) {
    checkWidget();
    count = Math.max(0, count);
    int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if (count == itemCount) {
      return;
    }
    boolean isVirtual = (style & SWT.VIRTUAL) != 0;
    if (!isVirtual) {
      setRedraw(false);
    }
    int index = count;
    while (index < itemCount) {
      TableItem item = items[index];
      if (!isVirtual) {
        ignoreSelect = ignoreShrink = true;
        int code = OS.SendMessage(handle, LVM_DELETEITEM, count, 0);
        ignoreSelect = ignoreShrink = false;
        if (code == 0) {
          break;
        }
      }
      if ((item != null) && (!item.isDisposed())) {
        item.releaseResources();
      }
      index++;
    }
    if (index < itemCount) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    TableItem[] newItems = new TableItem[((count + 3) / 4) * 4];
    System.arraycopy(items, 0, newItems, 0, Math.min(count, itemCount));
    items = newItems;
    if (isVirtual) {
      int flags = OS.LVSICF_NOINVALIDATEALL | OS.LVSICF_NOSCROLL;
      OS.SendMessage(handle, LVM_SETITEMCOUNT, count, flags);
    } else {
      for (int i = itemCount; i < count; i++) {
        items[i] = new TableItem(this, SWT.NONE, i, true);
      }
    }
    if (!isVirtual) {
      setRedraw(true);
    }
  }
}
