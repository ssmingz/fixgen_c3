class PlaceHold {
  int textCellDataProc(int tree_column, int cell, int tree_model, int iter, int data) {
    if (cell == ignoreTextCell) {
      return 0;
    }
    int modelIndex = -1;
    boolean customDraw = false;
    if (columnCount == 0) {
      modelIndex = Tree.FIRST_COLUMN;
      customDraw = firstCustomDraw;
    } else {
      for (int i = 0; i < columns.length; i++) {
        if ((columns[i] != null) && (columns[i].handle == tree_column)) {
          modelIndex = columns[i].modelIndex;
          customDraw = columns[i].customDraw;
          break;
        }
      }
    }
    if (modelIndex == (-1)) {
      return 0;
    }
    boolean setData = false;
    if ((style & SWT.VIRTUAL) != 0) {}
    int[] ptr = new int[1];
    if (setData) {
      OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_TEXT, ptr, -1);
      if (ptr[0] != 0) {
        OS.g_object_set(cell, text, ptr[0], 0);
        OS.g_free(ptr[0]);
      }
      ptr = new int[1];
    }
    if (customDraw) {
      OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_FOREGROUND, ptr, -1);
      if (ptr[0] != 0) {
        OS.g_object_set(cell, foreground_gdk, ptr[0], 0);
      }
      if (OS.GTK_VERSION > OS.VERSION(2, 2, 1)) {
        ptr = new int[1];
        OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_BACKGROUND, ptr, -1);
        if (ptr[0] != 0) {
          OS.g_object_set(cell, background_gdk, ptr[0], 0);
        }
      }
      ptr = new int[1];
      OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_FONT, ptr, -1);
      if (ptr[0] != 0) {
        OS.g_object_set(cell, font_desc, ptr[0], 0);
      }
    }
    if (setData) {
      ignoreTextCell = cell;
      setScrollWidth(tree_column, iter);
      ignoreTextCell = 0;
    }
    return 0;
  }
}
