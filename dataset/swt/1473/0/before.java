class PlaceHold {
  int findPart(int column, String partName) {
    if (!OS.FrameworkElement_IsLoaded(handle)) {
      OS.UIElement_UpdateLayout(handle);
    }
    if (!OS.FrameworkElement_IsLoaded(handle)) {
      return 0;
    }
    int rowPresenterType = OS.GridViewRowPresenter_typeid();
    int rowPresenter = findRowPresenter(handle, rowPresenterType);
    int contentPresenter = OS.VisualTreeHelper_GetChild(rowPresenter, column);
    int columns = OS.GridView_Columns(parent.gridViewHandle);
    int columnHandle = OS.GridViewColumnCollection_default(columns, column);
    int cellTemplate = OS.GridViewColumn_CellTemplate(columnHandle);
    int name = createDotNetString(partName, false);
    int result = OS.FrameworkTemplate_FindName(cellTemplate, name, contentPresenter);
    OS.GCHandle_Free(rowPresenterType);
    OS.GCHandle_Free(rowPresenter);
    OS.GCHandle_Free(contentPresenter);
    OS.GCHandle_Free(columns);
    OS.GCHandle_Free(columnHandle);
    OS.GCHandle_Free(cellTemplate);
    OS.GCHandle_Free(name);
    return result;
  }
}
