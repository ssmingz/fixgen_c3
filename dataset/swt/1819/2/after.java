class PlaceHold {
  void checkItems(boolean setScrollWidth) {
    int[] count = new int[1];
    if (OS.GetDataBrowserItemCount(
            handle, kDataBrowserNoItem, true, kDataBrowserItemAnyState, count)
        != OS.noErr) {
      error(ERROR_CANNOT_GET_COUNT);
    }
    if (itemCount != count[0]) {
      DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
      OS.GetDataBrowserCallbacks(handle, callbacks);
      callbacks.v1_itemNotificationCallback = 0;
      callbacks.v1_itemCompareCallback = 0;
      OS.SetDataBrowserCallbacks(handle, callbacks);
      int delta = itemCount - count[0];
      if (delta < 1024) {
        int[] ids = new int[delta];
        for (int i = 0; i < ids.length; i++) {
          ids[i] = (count[0] + i) + 1;
        }
        if (OS.AddDataBrowserItems(
                handle, kDataBrowserNoItem, ids.length, ids, kDataBrowserItemNoProperty)
            != OS.noErr) {
          error(ERROR_ITEM_NOT_ADDED);
        }
        OS.UpdateDataBrowserItems(
            handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
      } else if (OS.AddDataBrowserItems(handle, 0, itemCount, null, kDataBrowserItemNoProperty)
          != OS.noErr) {
        error(ERROR_ITEM_NOT_ADDED);
      }
      callbacks.v1_itemNotificationCallback = display.itemNotificationProc;
      callbacks.v1_itemCompareCallback = itemCompareProc();
      OS.SetDataBrowserCallbacks(handle, callbacks);
    }
    if (setScrollWidth) {
      setScrollWidth(items, true);
    }
  }
}
