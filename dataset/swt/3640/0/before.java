class PlaceHold {
  public String getText(int index) {
    checkWidget();
    if (!parent.checkData(this)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (!((0 <= index) && (index <= parent.columnCount))) {
      return "";
    }
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      error(ERROR_CANNOT_GET_TEXT);
    }
    int[] ptr = new int[1];
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_tree_model_get(parent.modelHandle, handle, modelIndex + 1, ptr, -1);
    if (ptr[0] == 0) {
      return "";
    }
    int length = OS.strlen(ptr[0]);
    byte[] buffer = new byte[length];
    OS.memmove(buffer, ptr[0], length);
    OS.g_free(ptr[0]);
    return new String(Converter.mbcsToWcs(null, buffer));
  }
}
