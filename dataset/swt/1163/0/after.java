class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return;
    }
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    int fontHandle = (font != null) ? font.handle : 0;
    OS.gtk_list_store_set(parent.modelHandle, handle, modelIndex + 4, fontHandle, -1);
    cached = true;
    if (font != null) {
      boolean customDraw =
          (parent.columnCount == 0) ? parent.firstCustomDraw : parent.columns[index].customDraw;
      if (!customDraw) {
        int list = OS.gtk_tree_view_column_get_cell_renderers(column);
        int length = OS.g_list_length(list);
        int imageRenderer = OS.g_list_nth_data(list, length - 2);
        int textRenderer = OS.g_list_nth_data(list, length - 1);
        OS.g_list_free(list);
        if ((parent.style & SWT.VIRTUAL) == 0) {
          OS.gtk_tree_view_column_set_cell_data_func(
              column, imageRenderer, pixbufCellDataProc, parent.handle, 0);
          OS.gtk_tree_view_column_set_cell_data_func(
              column, textRenderer, textCellDataProc, parent.handle, 0);
        }
        if (parent.columnCount == 0) {
          parent.firstCustomDraw = true;
        } else {
          parent.columns[index].customDraw = true;
        }
      }
    }
  }
}
