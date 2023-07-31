class PlaceHold {
  void redraw(int propertyID) {
    if (parent.ignoreRedraw) {
      return;
    }
    if ((!getDrawing()) && (propertyID != Tree.CHECK_COLUMN_ID)) {
      return;
    }
    int parentHandle = parent.handle;
    int parentID = (parentItem == null) ? OS.kDataBrowserNoItem : parentItem.id;
    DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
    OS.GetDataBrowserCallbacks(parent.handle, callbacks);
    callbacks.v1_itemCompareCallback = 0;
    OS.SetDataBrowserCallbacks(parent.handle, callbacks);
    int[] ids = new int[] {id};
    if (propertyID == OS.kDataBrowserNoItem) {
      if ((parent.style & SWT.CHECK) != 0) {
        OS.UpdateDataBrowserItems(
            parentHandle, parentID, ids.length, ids, kDataBrowserItemNoProperty, CHECK_COLUMN_ID);
      }
      if (parent.columnCount == 0) {
        OS.UpdateDataBrowserItems(
            parentHandle, parentID, ids.length, ids, kDataBrowserItemNoProperty, parent.column_id);
      } else {
        for (int i = 0; i < parent.columnCount; i++) {
          OS.UpdateDataBrowserItems(
              parentHandle,
              parentID,
              ids.length,
              ids,
              kDataBrowserItemNoProperty,
              parent.columns[i].id);
        }
      }
    } else {
      OS.UpdateDataBrowserItems(
          parentHandle, parentID, ids.length, ids, kDataBrowserItemNoProperty, propertyID);
    }
    callbacks.v1_itemCompareCallback = display.itemCompareProc;
    OS.SetDataBrowserCallbacks(parent.handle, callbacks);
    if (propertyID == Tree.CHECK_COLUMN_ID) {
      Rect rect = new Rect();
      if (OS.GetDataBrowserItemPartBounds(
              parentHandle, id, propertyID, kDataBrowserPropertyEnclosingPart, rect)
          == OS.noErr) {
        int x = rect.left;
        int y = rect.top - 1;
        int width = rect.right - rect.left;
        int height = 1;
        redrawWidget(parentHandle, x, y, width, height, false);
      }
    }
  }
}
