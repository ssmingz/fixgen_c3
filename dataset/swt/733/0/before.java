class PlaceHold {
  public void setBackground(int index, Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    GdkColor gdkColor = (color != null) ? color.handle : null;
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return;
    }
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_list_store_set(parent.modelHandle, handle, modelIndex + 3, gdkColor, -1);
    if (color != null) {
      boolean customDraw =
          (parent.columnCount == 0) ? parent.firstCustomDraw : parent.columns[index].customDraw;
      if (!customDraw) {
        int list = OS.gtk_tree_view_column_get_cell_renderers(column);
        int length = OS.g_list_length(list);
        int renderer = OS.g_list_nth_data(list, length - 1);
        OS.g_list_free(list);
        OS.gtk_tree_view_column_set_cell_data_func(
            column, renderer, cellDataProc, parent.handle, 0);
        if (parent.columnCount == 0) {
          parent.firstCustomDraw = true;
        } else {
          parent.columns[index].customDraw = true;
        }
      }
    }
  }
}
