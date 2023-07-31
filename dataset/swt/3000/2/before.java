class PlaceHold {
  void createHandle() {
    if (handle == 0) {
      handle = OS.gcnew_TreeViewItem();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      if (parent.columnCount != 0) {
        int headerHandle = OS.gcnew_SWTTreeViewRowPresenter(parent.handle);
        if (headerHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.GridViewRowPresenterBase_Columns(headerHandle, parent.columns);
        OS.HeaderedItemsControl_Header(handle, headerHandle);
        OS.GCHandle_Free(headerHandle);
      } else {
        OS.TreeViewItem_HeaderTemplate(handle, parent.headerTemplate);
      }
    }
    OS.Control_HorizontalContentAlignment(handle, HorizontalAlignment_Stretch);
    OS.Control_VerticalContentAlignment(handle, VerticalAlignment_Stretch);
    updateCheck();
    fixStyle();
  }
}
