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
    if (cellFont == null) {
      cellFont = new Font[count];
    }
    if (cellFont[index] == font) {
      return;
    }
    if ((cellFont[index] != null) && cellFont[index].equals(font)) {
      return;
    }
    cellFont[index] = font;
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return;
    }
    int modelIndex =
        (parent.columnCount == 0) ? Tree.FIRST_COLUMN : parent.columns[index].modelIndex;
    int fontHandle = (font != null) ? font.handle : 0;
    OS.gtk_tree_store_set(parent.modelHandle, handle, modelIndex + Tree.CELL_FONT, fontHandle, -1);
    cached = true;
    if (font != null) {
      boolean customDraw =
          (parent.columnCount == 0) ? parent.firstCustomDraw : parent.columns[index].customDraw;
      if (!customDraw) {
        if ((parent.style & SWT.VIRTUAL) == 0) {
          int textRenderer = parent.getTextRenderer(column);
          int imageRenderer = parent.getPixbufRenderer(column);
          OS.gtk_tree_view_column_set_cell_data_func(
              column, textRenderer, textCellDataProc, parent.handle, 0);
          OS.gtk_tree_view_column_set_cell_data_func(
              column, imageRenderer, pixbufCellDataProc, parent.handle, 0);
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
