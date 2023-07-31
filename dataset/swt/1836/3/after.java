class PlaceHold {
  void setItemCount(TreeItem parentItem, int count) {
    int itemCount = (parentItem != null) ? parentItem.itemCount : this.itemCount;
    count = Math.max(0, count);
    if (count == itemCount) {
      return;
    }
    int parentHandle = (parentItem != null) ? parentItem.handle : handle;
    int index = itemCount - 1;
    int items = OS.ItemsControl_Items(parentHandle);
    while (index >= count) {
      TreeItem item = getItem(items, index, false);
      if (item != null) {
        if (!item.isDisposed()) {
          item.release(true);
        }
      } else {
        OS.ItemCollection_RemoveAt(items, index);
      }
      index--;
    }
    if (OS.ItemCollection_Count(items) > count) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    if ((style & SWT.VIRTUAL) != 0) {
      for (int i = itemCount; i < count; i++) {
        int item = OS.gcnew_TreeViewItem();
        if (item == 0) {
          error(ERROR_NO_HANDLES);
        }
        if (columnCount != 0) {
          int headerHandle = OS.gcnew_SWTTreeViewRowPresenter(handle);
          if (headerHandle == 0) {
            error(ERROR_NO_HANDLES);
          }
          OS.GridViewRowPresenterBase_Columns(headerHandle, gvColumns);
          OS.HeaderedItemsControl_Header(item, headerHandle);
          OS.GCHandle_Free(headerHandle);
        } else {
          OS.TreeViewItem_HeaderTemplate(item, headerTemplate);
        }
        OS.ItemCollection_Add(items, item);
        OS.GCHandle_Free(item);
      }
    } else {
      for (int i = itemCount; i < count; i++) {
        new TreeItem(this, parentItem, SWT.NONE, i, 0);
      }
    }
    itemCount = OS.ItemCollection_Count(items);
    OS.GCHandle_Free(items);
    if (itemCount != count) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    if (parentItem != null) {
      parentItem.itemCount = itemCount;
    } else {
      this.itemCount = itemCount;
    }
  }
}
