class PlaceHold {
  public Image getImage(int index) {
    checkWidget();
    if (!parent.checkData(this)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index <= parent.columnCount))) {
      return null;
    }
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return null;
    }
    int[] ptr = new int[1];
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_tree_model_get(parent.modelHandle, handle, modelIndex, ptr, -1);
    if (ptr[0] == 0) {
      return null;
    }
    ImageList imageList = parent.imageList;
    int imageIndex = imageList.indexOf(ptr[0]);
    if (imageIndex == (-1)) {
      return null;
    }
    return imageList.get(imageIndex);
  }
}
