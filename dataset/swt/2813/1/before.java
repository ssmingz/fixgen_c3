class PlaceHold {
  void createColumn(TreeColumn column, int index) {
    int modelIndex = FIRST_COLUMN;
    if (columnCount != 0) {
      int modelLength = OS.gtk_tree_model_get_n_columns(modelHandle);
      boolean[] usedColumns = new boolean[modelLength];
      for (int i = 0; i < columnCount; i++) {
        int columnIndex = columns[i].modelIndex;
        for (int j = 0; j < CELL_TYPES; j++) {
          usedColumns[columnIndex + j] = true;
        }
      }
      while (modelIndex < modelLength) {
        if (!usedColumns[modelIndex]) {
          break;
        }
        modelIndex++;
      }
      if (modelIndex == modelLength) {
        int oldModel = modelHandle;
        int[] types = getColumnTypes(columnCount + 4);
        int newModel = OS.gtk_tree_store_newv(types.length, types);
        if (newModel == 0) {
          error(ERROR_NO_HANDLES);
        }
        copyModel(
            oldModel,
            FIRST_COLUMN,
            newModel,
            FIRST_COLUMN,
            types,
            ((int) (0)),
            ((int) (0)),
            modelLength);
        OS.gtk_tree_view_set_model(handle, newModel);
        OS.g_object_unref(oldModel);
        modelHandle = newModel;
      }
    }
    int columnHandle = OS.gtk_tree_view_column_new();
    if (columnHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((index == 0) && (columnCount > 0)) {
      TreeColumn checkColumn = columns[0];
      createRenderers(checkColumn.handle, checkColumn.modelIndex, false, checkColumn.style);
    }
    createRenderers(columnHandle, modelIndex, index == 0, column == null ? 0 : column.style);
    if (((style & SWT.VIRTUAL) == 0) && (columnCount == 0)) {
      OS.gtk_tree_view_column_set_sizing(columnHandle, GTK_TREE_VIEW_COLUMN_GROW_ONLY);
    } else {
      OS.gtk_tree_view_column_set_sizing(columnHandle, GTK_TREE_VIEW_COLUMN_FIXED);
      if (columnCount != 0) {
        OS.gtk_tree_view_column_set_visible(columnHandle, false);
      }
    }
    OS.gtk_tree_view_column_set_resizable(columnHandle, true);
    OS.gtk_tree_view_column_set_clickable(columnHandle, true);
    OS.gtk_tree_view_column_set_min_width(columnHandle, 0);
    OS.gtk_tree_view_insert_column(handle, columnHandle, index);
    if (column != null) {
      column.handle = columnHandle;
      column.modelIndex = modelIndex;
    }
    if (!searchEnabled()) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 6, 5)) {
        OS.gtk_tree_view_set_search_column(handle, -1);
      } else {
        OS.gtk_tree_view_set_enable_search(handle, false);
      }
    } else {
      int firstColumn = (columnCount == 0) ? FIRST_COLUMN : columns[0].modelIndex;
      OS.gtk_tree_view_set_search_column(handle, firstColumn + CELL_TEXT);
    }
  }
}
