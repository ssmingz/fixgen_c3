class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((image != null) && (image.type == SWT.ICON)) {
      if (image.equals(_getImage(index))) {
        return;
      }
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    int pixbuf = 0;
    if (image != null) {
      ImageList imageList = parent.imageList;
      if (imageList == null) {
        imageList = parent.imageList = new ImageList();
      }
      int imageIndex = imageList.indexOf(image);
      if (imageIndex == (-1)) {
        imageIndex = imageList.add(image);
      }
      pixbuf = imageList.getPixbuf(imageIndex);
    }
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_list_store_set(parent.modelHandle, handle, modelIndex + Table.CELL_PIXBUF, pixbuf, -1);
    if ((parent.style & SWT.VIRTUAL) != 0) {
      if ((OS.GTK_VERSION >= OS.VERSION(2, 3, 2)) && (OS.GTK_VERSION < OS.VERSION(2, 6, 3))) {
        redraw();
      }
    }
    if (((parent.style & SWT.VIRTUAL) != 0) && (parent.currentItem == null)) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 3, 2)) {
        if (image != null) {
          int parentHandle = parent.handle;
          int column = OS.gtk_tree_view_get_column(parentHandle, index);
          int[] w = new int[1];
          int pixbufRenderer = parent.getPixbufRenderer(column);
          OS.gtk_tree_view_column_cell_get_position(column, pixbufRenderer, null, w);
          if (w[0] < image.getBounds().width) {
            int style = OS.gtk_widget_get_modifier_style(parentHandle);
            OS.gtk_widget_modify_style(parentHandle, style);
          }
        }
      }
    }
    cached = true;
  }
}
