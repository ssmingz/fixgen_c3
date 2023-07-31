class PlaceHold {
  public boolean getHeaderVisible() {
    checkWidget();
    int column = OS.GridViewColumnCollection_default(gvColumns, 0);
    int header = OS.GridViewColumn_Header(column);
    boolean visible = OS.UIElement_Visibility(header) == OS.Visibility_Visible;
    OS.GCHandle_Free(header);
    OS.GCHandle_Free(column);
    return visible;
  }
}
