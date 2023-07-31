class PlaceHold {
  public static synchronized void put(int handle, Widget widget) {
    if (handle == 0) {
      return;
    }
    if (FreeSlot == (-1)) {
      int length = (FreeSlot = WidgetTable.IndexTable.length) + GrowSize;
      int[] newIndexTable = new int[length];
      Widget[] newWidgetTable = new Widget[length];
      System.arraycopy(IndexTable, 0, newIndexTable, 0, FreeSlot);
      System.arraycopy(WidgetTable, 0, newWidgetTable, 0, FreeSlot);
      for (int i = FreeSlot; i < (length - 1); i++) {
        newIndexTable[i] = i + 1;
      }
      newIndexTable[length - 1] = -1;
      IndexTable = newIndexTable;
      WidgetTable = newWidgetTable;
    }
    int index = FreeSlot + 1;
    OS.g_object_set_qdata(handle, SWT_OBJECT_INDEX, index);
    int oldSlot = FreeSlot;
    FreeSlot = IndexTable[oldSlot];
    IndexTable[oldSlot] = -2;
    WidgetTable[oldSlot] = widget;
  }
}
