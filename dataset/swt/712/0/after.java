class PlaceHold {
  void destroyItem(TabItem item) {
    int[] args = new int[] {OS.Pt_ARG_PG_PANEL_TITLES, 0, 0};
    OS.PtGetResources(handle, args.length / 3, args);
    int count = itemCount;
    int index = 0;
    while (index < count) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    int oldPtr = args[1];
    int newPtr = OS.malloc((count - 1) * 4);
    if (newPtr == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    int offset = 0;
    for (int i = 0; i < count; i++) {
      if (i == index) {
        offset = -1;
        continue;
      }
      int[] address = new int[1];
      OS.memmove(address, oldPtr + (i * 4), 4);
      int length = OS.strlen(address[0]);
      int str = OS.malloc(length + 1);
      if (str == 0) {
        error(ERROR_ITEM_NOT_ADDED);
      }
      OS.memmove(str, address[0], length + 1);
      OS.memmove(newPtr + ((i + offset) * 4), new int[] {str}, 4);
    }
    OS.PtSetResource(handle, Pt_ARG_PG_PANEL_TITLES, newPtr, count - 1);
    for (int i = 0; i < (count - 1); i++) {
      int[] address = new int[1];
      OS.memmove(address, newPtr + (i * 4), 4);
      OS.free(address[0]);
    }
    OS.free(newPtr);
    if (index != count) {
      System.arraycopy(items, index + 1, items, index, (--count) - index);
    }
    items[count] = null;
    itemCount--;
  }
}
