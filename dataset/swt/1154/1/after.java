class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    int style = 0;
    if (!show) {
      style = OS.gcnew_Style();
      int dp = OS.UIElement_VisibilityProperty();
      int setter = OS.gcnew_SetterVisibility(dp, Visibility_Collapsed);
      int collection = OS.Style_Setters(style);
      OS.SetterBaseCollection_Add(collection, setter);
      OS.GCHandle_Free(collection);
      OS.GCHandle_Free(setter);
      OS.GCHandle_Free(dp);
    }
    OS.GridView_ColumnHeaderContainerStyle(gridViewHandle, style);
    if (style != 0) {
      OS.GCHandle_Free(style);
    }
    for (int i = 0; i < columnCount; i++) {
      TableColumn column = getColumn(i);
      column.updateImage();
      column.updateText();
    }
  }
}
