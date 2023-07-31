class PlaceHold {
  void OnRender(int source, int dc) {
    int type = OS.ListViewItem_typeid();
    int itemHandle = findPartByType(source, type);
    OS.GCHandle_Free(type);
    TableItem item = getItem(itemHandle, true);
    OS.GCHandle_Free(itemHandle);
    if (item.cached && (item.rowHandle != 0)) {
      return;
    }
    checkData(item);
    if (item.rowHandle == 0) {
      int rowPresenterType = OS.GridViewRowPresenter_typeid();
      item.rowHandle = item.findRowPresenter(item.handle, rowPresenterType);
      OS.GCHandle_Free(rowPresenterType);
    }
    int columns = (columnCount == 0) ? 1 : columnCount;
    item.updateCheck();
    for (int i = 0; i < columns; i++) {
      item.updateText(i);
      item.updateImage(i);
      item.updateBackground(i);
      item.updateForeground(i);
      item.updateFont(i);
    }
  }
}
