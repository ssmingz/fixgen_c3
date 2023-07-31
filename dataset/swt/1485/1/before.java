class PlaceHold {
  void createItem(TreeColumn column, int index) {
    if (!((0 <= index) && (index <= columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (index == 0) {
      column.style &= ~((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
      column.style |= SWT.LEFT;
    }
    if (columnCount == 0) {
      column.handle = OS.gtk_tree_view_get_column(handle, 0);
      OS.gtk_tree_view_column_set_sizing(column.handle, GTK_TREE_VIEW_COLUMN_FIXED);
      OS.gtk_tree_view_column_set_visible(column.handle, false);
      column.modelIndex = FIRST_COLUMN;
      createRenderers(column.handle, column.modelIndex, true, column.style);
      column.customDraw = firstCustomDraw;
      firstCustomDraw = false;
    } else {
      createColumn(column, index);
    }
    int boxHandle = OS.gtk_hbox_new(false, 3);
    if (boxHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int labelHandle = OS.gtk_label_new_with_mnemonic(null);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int imageHandle = OS.gtk_image_new();
    if (imageHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(boxHandle, imageHandle);
    OS.gtk_container_add(boxHandle, labelHandle);
    OS.gtk_widget_show(boxHandle);
    OS.gtk_widget_show(labelHandle);
    column.labelHandle = labelHandle;
    column.imageHandle = imageHandle;
    OS.gtk_tree_view_column_set_widget(column.handle, boxHandle);
    int widget = OS.gtk_widget_get_parent(boxHandle);
    while (widget != handle) {
      if (OS.GTK_IS_BUTTON(widget)) {
        column.buttonHandle = widget;
        break;
      }
      widget = OS.gtk_widget_get_parent(widget);
    }
    if (columnCount == columns.length) {
      TreeColumn[] newColumns = new TreeColumn[columns.length + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    System.arraycopy(columns, index, columns, index + 1, (columnCount++) - index);
    columns[index] = column;
    if ((state & FONT) != 0) {
      column.setFontDescription(getFontDescription());
    }
    if (columnCount >= 1) {
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        if (item != null) {
          Font[] cellFont = item.cellFont;
          if (cellFont != null) {
            Font[] temp = new Font[columnCount];
            System.arraycopy(cellFont, 0, temp, 0, index);
            System.arraycopy(cellFont, index, temp, index + 1, (columnCount - index) - 1);
            item.cellFont = temp;
          }
        }
      }
    }
  }
}
