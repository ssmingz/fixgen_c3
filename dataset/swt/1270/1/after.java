class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (!((0 <= index) && (index <= parent.columnCount))) {
      return;
    }
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
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
    cached = true;
  }
}
