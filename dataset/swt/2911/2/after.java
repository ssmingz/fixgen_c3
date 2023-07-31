class PlaceHold {
  void createColumn(TableColumn column, int index) {
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
        int newModel = OS.gtk_list_store_newv(types.length, types);
        if (newModel == 0) {
          error(ERROR_NO_HANDLES);
        }
        int[] ptr = new int[1];
        for (int i = 0; i < itemCount; i++) {
          int newItem = OS.g_malloc(OS.GtkTreeIter_sizeof());
          if (newItem == 0) {
            error(ERROR_NO_HANDLES);
          }
          OS.gtk_list_store_append(newModel, newItem);
          TableItem item = items[i];
          if (item != null) {
            int oldItem = item.handle;
            for (int j = 0; j < modelLength; j++) {
              OS.gtk_tree_model_get(oldModel, oldItem, j, ptr, -1);
              OS.gtk_list_store_set(newModel, newItem, j, ptr[0], -1);
              if (types[j] == OS.G_TYPE_STRING()) {
                OS.g_free(ptr[0]);
              } else if (ptr[0] != 0) {
                if (types[j] == OS.GDK_TYPE_COLOR()) {
                  OS.gdk_color_free(ptr[0]);
                } else if (types[j] == OS.GDK_TYPE_PIXBUF()) {
                  OS.g_object_unref(ptr[0]);
                } else if (types[j] == OS.PANGO_TYPE_FONT_DESCRIPTION()) {
                  OS.pango_font_description_free(ptr[0]);
                }
              }
            }
            OS.gtk_list_store_remove(oldModel, oldItem);
            OS.g_free(oldItem);
            item.handle = newItem;
          } else {
            OS.g_free(newItem);
          }
        }
        OS.gtk_tree_view_set_model(handle, newModel);
        setModel(newModel);
      }
    }
    int columnHandle = OS.gtk_tree_view_column_new();
    if (columnHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((index == 0) && (columnCount > 0)) {
      TableColumn checkColumn = columns[0];
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
