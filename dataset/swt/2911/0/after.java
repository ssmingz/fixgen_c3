class PlaceHold {
  void destroyItem(TreeColumn column) {
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
      int newModel = OS.gtk_tree_store_newv(types.length, types);
      if (newModel == 0) {
        error(ERROR_NO_HANDLES);
      }
      copyModel(
          oldModel,
          column.modelIndex,
          newModel,
          FIRST_COLUMN,
          types,
          ((int) (0)),
          ((int) (0)),
          FIRST_COLUMN + CELL_TYPES);
      OS.gtk_tree_view_set_model(handle, newModel);
      setModel(newModel);
      createColumn(null, 0);
    } else {
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        if (item != null) {
          int iter = item.handle;
          int modelIndex = column.modelIndex;
          OS.gtk_tree_store_set(modelHandle, iter, modelIndex + CELL_PIXBUF, 0, -1);
          OS.gtk_tree_store_set(modelHandle, iter, modelIndex + CELL_TEXT, 0, -1);
          OS.gtk_tree_store_set(modelHandle, iter, modelIndex + CELL_FOREGROUND, 0, -1);
          OS.gtk_tree_store_set(modelHandle, iter, modelIndex + CELL_BACKGROUND, 0, -1);
          OS.gtk_tree_store_set(modelHandle, iter, modelIndex + CELL_FONT, 0, -1);
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
        TreeColumn firstColumn = columns[0];
        firstColumn.style &= ~((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
        firstColumn.style |= SWT.LEFT;
        createRenderers(firstColumn.handle, firstColumn.modelIndex, true, firstColumn.style);
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
