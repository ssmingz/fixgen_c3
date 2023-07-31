class PlaceHold {
  public boolean getHeaderVisible() {
    checkWidget();
    if (columnCount == 0) {
      return false;
    }
    int columns = OS.GridView_Columns(gridViewHandle);
    int column = OS.GridViewColumnCollection_default(columns, 0);
    int header = OS.GridViewColumn_Header(column);
    boolean visible = OS.UIElement_Visibility(header) == OS.Visibility_Visible;
    OS.GCHandle_Free(header);
    OS.GCHandle_Free(column);
    OS.GCHandle_Free(columns);
    return visible;
  }
}
