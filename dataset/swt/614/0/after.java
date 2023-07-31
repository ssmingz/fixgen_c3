class PlaceHold {
  public String getItem(int index) {
    checkWidget();
    if (!((0 <= index) && (index < OS.GTK_CLIST_ROWS(handle)))) {
      error(ERROR_INVALID_RANGE);
    }
    int[] buffer = new int[1];
    int result = OS.gtk_clist_get_text(handle, index, 0, buffer);
    int length = OS.strlen(buffer[0]);
    byte[] buffer1 = new byte[length];
    OS.memmove(buffer1, buffer[0], length);
    char[] buffer2 = Converter.mbcsToWcs(null, buffer1);
    return new String(buffer2, 0, buffer2.length);
  }
}
