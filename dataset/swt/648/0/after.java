class PlaceHold {
  void OnRender(int source, int dc) {
    int type = OS.TreeViewItem_typeid();
    int itemHandle = findPartOfType(source, type);
    OS.GCHandle_Free(type);
    TreeItem item = getItem(itemHandle, true);
    OS.GCHandle_Free(itemHandle);
    if ((item.cached || ((style & SWT.VIRTUAL) == 0)) && (item.contentHandle != 0)) {
      return;
    }
    checkData(item);
    if (item.contentHandle == 0) {
      item.contentHandle = item.findContentPresenter();
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
