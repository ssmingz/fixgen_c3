class PlaceHold {
  long rendererGetSizeProc(
      long cell,
      long widget,
      long cell_area,
      long x_offset,
      long y_offset,
      long width,
      long height) {
    long g_class = OS.g_type_class_peek_parent(OS.G_OBJECT_GET_CLASS(cell));
    GtkCellRendererClass klass = new GtkCellRendererClass();
    OS.memmove(klass, g_class);
    OS.call_get_size(klass.get_size, cell, handle, cell_area, x_offset, y_offset, width, height);
    if ((!ignoreSize) && OS.GTK_IS_CELL_RENDERER_TEXT(cell)) {
      long iter = OS.g_object_get_qdata(cell, SWT_OBJECT_INDEX2);
      TableItem item = null;
      boolean isSelected = false;
      if (iter != 0) {
        long path = OS.gtk_tree_model_get_path(modelHandle, iter);
        int[] buffer = new int[1];
        OS.memmove(buffer, OS.gtk_tree_path_get_indices(path), 4);
        int index = buffer[0];
        item = _getItem(index);
        long selection = OS.gtk_tree_view_get_selection(handle);
        isSelected = OS.gtk_tree_selection_path_is_selected(selection, path);
        OS.gtk_tree_path_free(path);
      }
      if (item != null) {
        int columnIndex = 0;
        if (columnCount > 0) {
          long columnHandle = OS.g_object_get_qdata(cell, SWT_OBJECT_INDEX1);
          for (int i = 0; i < columnCount; i++) {
            if (columns[i].handle == columnHandle) {
              columnIndex = i;
              break;
            }
          }
        }
        if (hooks(MeasureItem)) {
          int[] contentWidth = new int[1];
          int[] contentHeight = new int[1];
          if (width != 0) {
            OS.memmove(contentWidth, width, 4);
          }
          if (height != 0) {
            OS.memmove(contentHeight, height, 4);
          }
          Image image = item.getImage(columnIndex);
          int imageWidth = 0;
          if (image != null) {
            Rectangle bounds = image.getBounds();
            imageWidth = bounds.width;
          }
          contentWidth[0] += imageWidth;
          GC gc = new GC(this);
          gc.setFont(item.getFont(columnIndex));
          Event event = new Event();
          event.item = item;
          event.index = columnIndex;
          event.gc = gc;
          event.width = contentWidth[0];
          event.height = contentHeight[0];
          if (isSelected) {
            event.detail = SWT.SELECTED;
          }
          sendEvent(MeasureItem, event);
          gc.dispose();
          contentWidth[0] = event.width - imageWidth;
          if (contentHeight[0] < event.height) {
            contentHeight[0] = event.height;
          }
          if (width != 0) {
            OS.memmove(width, contentWidth, 4);
          }
          if (height != 0) {
            OS.memmove(height, contentHeight, 4);
          }
        }
      }
    }
    return 0;
  }
}
