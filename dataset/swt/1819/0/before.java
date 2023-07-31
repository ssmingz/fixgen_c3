class PlaceHold {
  public void setItemCount(int count) {
    checkWidget();
    checkItems(true);
    count = Math.max(0, count);
    if (count == itemCount) {
      return;
    }
    setRedraw(false);
    int[] top = new int[1];
    int[] left = new int[1];
    OS.GetDataBrowserScrollPosition(handle, top, left);
    DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
    OS.GetDataBrowserCallbacks(handle, callbacks);
    callbacks.v1_itemNotificationCallback = 0;
    callbacks.v1_itemCompareCallback = 0;
    OS.SetDataBrowserCallbacks(handle, callbacks);
    if (count < itemCount) {
      int index = count;
      while (index < itemCount) {
        TableItem item = items[index];
        if (item != null) {
          item.release(false);
        }
        int[] id = new int[] {index + 1};
        if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, id.length, id, 0) != OS.noErr) {
          break;
        }
        index++;
      }
      if (index < itemCount) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    }
    int length = Math.max(4, ((count + 3) / 4) * 4);
    TableItem[] newItems = new TableItem[length];
    System.arraycopy(items, 0, newItems, 0, Math.min(count, itemCount));
    items = newItems;
    if ((style & SWT.VIRTUAL) == 0) {
      for (int i = itemCount; i < count; i++) {
        items[i] = new TableItem(this, SWT.NONE, i, false);
      }
    }
    itemCount = count;
    OS.AddDataBrowserItems(handle, 0, itemCount, null, kDataBrowserItemNoProperty);
    callbacks.v1_itemNotificationCallback = display.itemNotificationProc;
    callbacks.v1_itemCompareCallback = display.itemCompareProc;
    OS.SetDataBrowserCallbacks(handle, callbacks);
    fixScrollBar();
    setRedraw(true);
  }
}
