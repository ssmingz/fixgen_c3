class PlaceHold {
  int pixbufCellDataProc(int tree_column, int cell, int tree_model, int iter, int data) {
    if (cell == ignorePixbufCell) {
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
      OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_PIXBUF, ptr, -1);
      OS.g_object_set(cell, pixbuf, ptr[0], 0);
      ptr = new int[1];
    }
    if (customDraw) {
      if (OS.GTK_VERSION > OS.VERSION(2, 2, 1)) {
        OS.gtk_tree_model_get(tree_model, iter, modelIndex + CELL_BACKGROUND, ptr, -1);
        if (ptr[0] != 0) {
          OS.g_object_set(cell, cell_background_gdk, ptr[0], 0);
        }
      }
    }
    if (setData) {
      ignorePixbufCell = cell;
      setScrollWidth(tree_column, iter);
      ignorePixbufCell = 0;
    }
    return 0;
  }
}
