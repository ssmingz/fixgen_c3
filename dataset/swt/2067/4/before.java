class PlaceHold {
  String _getText(int index) {
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return "";
    }
    int[] ptr = new int[1];
    int modelIndex =
        (parent.columnCount == 0) ? Table.FIRST_COLUMN : parent.columns[index].modelIndex;
    OS.gtk_tree_model_get(parent.modelHandle, handle, modelIndex + Table.CELL_TEXT, ptr, -1);
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
