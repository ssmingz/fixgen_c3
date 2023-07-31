class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
    OS.GetDataBrowserCallbacks(handle, callbacks);
    callbacks.v1_itemNotificationCallback = 0;
    callbacks.v1_itemCompareCallback = 0;
    OS.SetDataBrowserCallbacks(handle, callbacks);
    OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, 0, null, 0);
    callbacks.v1_itemNotificationCallback = display.itemNotificationProc;
    callbacks.v1_itemCompareCallback = itemCompareProc();
    OS.SetDataBrowserCallbacks(handle, callbacks);
    setTableEmpty();
  }
}
