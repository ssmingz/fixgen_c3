class PlaceHold {
  int findPart(int column, String partName) {
    if (contentHandle == 0) {
      updateLayout(handle);
    }
    if (contentHandle == 0) {
      return 0;
    }
    int name = createDotNetString(partName, false);
    int result = 0;
    if (parent.columnCount == 0) {
      int template = OS.TreeViewItem_HeaderTemplate(handle);
      result = OS.FrameworkTemplate_FindName(template, name, contentHandle);
      OS.GCHandle_Free(template);
    } else {
      int rowPresenter = OS.HeaderedItemsControl_Header(handle);
      int contentPresenter = OS.VisualTreeHelper_GetChild(rowPresenter, column);
      OS.GCHandle_Free(rowPresenter);
      int columnHandle = OS.GridViewColumnCollection_default(parent.gvColumns, column);
      int template = OS.GridViewColumn_CellTemplate(columnHandle);
      OS.GCHandle_Free(columnHandle);
      result = OS.FrameworkTemplate_FindName(template, name, contentPresenter);
      OS.GCHandle_Free(contentPresenter);
      OS.GCHandle_Free(template);
    }
    OS.GCHandle_Free(name);
    return result;
  }
}
