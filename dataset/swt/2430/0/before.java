class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    super.setText(string);
    int index = parent.indexOf(this);
    int[] args = new int[] {OS.Pt_ARG_PG_PANEL_TITLES, 0, 0};
    OS.PtGetResources(parent.handle, args.length / 3, args);
    int count = args[2];
    int oldPtr = args[1];
    int newPtr = OS.malloc(count * 4);
    for (int i = 0; i < count; i++) {
      int str;
      if (i == index) {
        byte[] buffer = Converter.wcsToMbcs(null, string);
        str = OS.malloc(buffer.length + 1);
        OS.memmove(str, buffer, buffer.length);
      } else {
        int[] address = new int[1];
        OS.memmove(address, oldPtr + (i * 4), 4);
        int length = OS.strlen(address[0]);
        str = OS.malloc(length + 1);
        OS.memmove(str, address[0], length + 1);
      }
      OS.memmove(newPtr + (i * 4), new int[] {str}, 4);
    }
    OS.PtSetResource(parent.handle, Pt_ARG_PG_PANEL_TITLES, newPtr, count);
    for (int i = 0; i < count; i++) {
      int[] address = new int[1];
      OS.memmove(address, newPtr + (i * 4), 4);
      OS.free(address[0]);
    }
    OS.free(newPtr);
  }
}
