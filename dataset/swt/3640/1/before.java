class PlaceHold {
  public void setText(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (!((0 <= index) && (index <= parent.columnCount))) {
      return;
    }
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return;
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_list_store_set(parent.modelHandle, handle, modelIndex + 1, buffer, -1);
    cached = true;
  }
}
