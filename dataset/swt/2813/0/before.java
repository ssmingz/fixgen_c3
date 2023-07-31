class PlaceHold {
  void destroyItem(TableColumn column) {
    int index = 0;
    while (index < columnCount) {
      if (columns[index] == column) {
        break;
      }
      index++;
    }
    if (index == columnCount) {
      return;
    }
    int columnHandle = column.handle;
    if (columnCount == 1) {
      firstCustomDraw = column.customDraw;
    }
    System.arraycopy(columns, index + 1, columns, index, (--columnCount) - index);
    columns[columnCount] = null;
    OS.gtk_tree_view_remove_column(handle, columnHandle);
    if (columnCount == 0) {
      int oldModel = modelHandle;
      int[] types = getColumnTypes(1);
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
          for (int j = 0; j < FIRST_COLUMN; j++) {
            OS.gtk_tree_model_get(oldModel, oldItem, j, ptr, -1);
            OS.gtk_list_store_set(newModel, newItem, j, ptr[0], -1);
            if (ptr[0] != 0) {
              if ((j == FOREGROUND_COLUMN) || (j == BACKGROUND_COLUMN)) {
                OS.gdk_color_free(ptr[0]);
              } else if (j == FONT_COLUMN) {
                OS.pango_font_description_free(ptr[0]);
              }
            }
          }
          OS.gtk_tree_model_get(oldModel, oldItem, column.modelIndex + CELL_PIXBUF, ptr, -1);
          OS.gtk_list_store_set(newModel, newItem, FIRST_COLUMN + CELL_PIXBUF, ptr[0], -1);
          if (ptr[0] != 0) {
            OS.g_object_unref(ptr[0]);
          }
          OS.gtk_tree_model_get(oldModel, oldItem, column.modelIndex + CELL_TEXT, ptr, -1);
          OS.gtk_list_store_set(newModel, newItem, FIRST_COLUMN + CELL_TEXT, ptr[0], -1);
          OS.g_free(ptr[0]);
          OS.gtk_tree_model_get(oldModel, oldItem, column.modelIndex + CELL_FOREGROUND, ptr, -1);
          OS.gtk_list_store_set(newModel, newItem, FIRST_COLUMN + CELL_FOREGROUND, ptr[0], -1);
          if (ptr[0] != 0) {
            OS.gdk_color_free(ptr[0]);
          }
          OS.gtk_tree_model_get(oldModel, oldItem, column.modelIndex + CELL_BACKGROUND, ptr, -1);
          OS.gtk_list_store_set(newModel, newItem, FIRST_COLUMN + CELL_BACKGROUND, ptr[0], -1);
          if (ptr[0] != 0) {
            OS.gdk_color_free(ptr[0]);
          }
          OS.gtk_tree_model_get(oldModel, oldItem, column.modelIndex + CELL_FONT, ptr, -1);
          OS.gtk_list_store_set(newModel, newItem, FIRST_COLUMN + CELL_FONT, ptr[0], -1);
          if (ptr[0] != 0) {
            OS.pango_font_description_free(ptr[0]);
          }
          OS.gtk_list_store_remove(oldModel, oldItem);
          OS.g_free(oldItem);
          item.handle = newItem;
        } else {
          OS.g_free(newItem);
        }
      }
      OS.gtk_tree_view_set_model(handle, newModel);
      OS.g_object_unref(oldModel);
      modelHandle = newModel;
      createColumn(null, 0);
    } else {
      for (int i = 0; i < itemCount; i++) {
        TableItem item = items[i];
        if (item != null) {
          int iter = item.handle;
          int modelIndex = column.modelIndex;
          OS.gtk_list_store_set(modelHandle, iter, modelIndex + CELL_PIXBUF, 0, -1);
          OS.gtk_list_store_set(modelHandle, iter, modelIndex + CELL_TEXT, 0, -1);
          OS.gtk_list_store_set(modelHandle, iter, modelIndex + CELL_FOREGROUND, 0, -1);
          OS.gtk_list_store_set(modelHandle, iter, modelIndex + CELL_BACKGROUND, 0, -1);
          OS.gtk_list_store_set(modelHandle, iter, modelIndex + CELL_FONT, 0, -1);
          Font[] cellFont = item.cellFont;
          if (cellFont != null) {
            if (columnCount == 0) {
              item.cellFont = null;
            } else {
              Font[] temp = new Font[columnCount];
              System.arraycopy(cellFont, 0, temp, 0, index);
              System.arraycopy(cellFont, index + 1, temp, index, columnCount - index);
              item.cellFont = temp;
            }
          }
        }
      }
      if (index == 0) {
        TableColumn checkColumn = columns[0];
        createRenderers(checkColumn.handle, checkColumn.modelIndex, true, checkColumn.style);
      }
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
