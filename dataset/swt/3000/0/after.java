class PlaceHold {
  void columnAdded(int index) {
    if (parent.columnCount == 0) {
      int headerTemplate = OS.HeaderedItemsControl_HeaderTemplateProperty();
      OS.DependencyObject_ClearValue(handle, headerTemplate);
      OS.GCHandle_Free(headerTemplate);
      int header = OS.gcnew_SWTTreeViewRowPresenter(parent.handle);
      if (header == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.GridViewRowPresenterBase_Columns(header, parent.gvColumns);
      OS.HeaderedItemsControl_Header(handle, header);
      OS.GCHandle_Free(header);
    } else {
      int newLength = parent.columnCount + 1;
      if (strings != null) {
        String[] temp = new String[newLength];
        System.arraycopy(strings, 0, temp, 0, index);
        System.arraycopy(strings, index, temp, index + 1, parent.columnCount - index);
        strings = temp;
      }
      if (images != null) {
        Image[] temp = new Image[newLength];
        System.arraycopy(images, 0, temp, 0, index);
        System.arraycopy(images, index, temp, index + 1, parent.columnCount - index);
        images = temp;
      }
      if (cellBackground != null) {
        Color[] temp = new Color[newLength];
        System.arraycopy(cellBackground, 0, temp, 0, index);
        System.arraycopy(cellBackground, index, temp, index + 1, parent.columnCount - index);
        cellBackground = temp;
      }
      if (cellForeground != null) {
        Color[] temp = new Color[newLength];
        System.arraycopy(cellForeground, 0, temp, 0, index);
        System.arraycopy(cellForeground, index, temp, index + 1, parent.columnCount - index);
        cellForeground = temp;
      }
      if (cellFont != null) {
        Font[] temp = new Font[newLength];
        System.arraycopy(cellFont, 0, temp, 0, index);
        System.arraycopy(cellFont, index, temp, index + 1, parent.columnCount - index);
        cellFont = temp;
      }
    }
    int items = OS.ItemsControl_Items(handle);
    for (int i = 0; i < itemCount; i++) {
      TreeItem item = parent.getItem(items, i, false);
      if (item != null) {
        item.columnAdded(index);
      }
    }
    OS.GCHandle_Free(items);
  }
}
