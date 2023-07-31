class PlaceHold {
  public static synchronized void put(int handle, Widget widget) {
    if (handle == 0) {
      return;
    }
    if (OS.XtIsSubclass(handle, OS.shellWidgetClass())) {
      for (int i = 0; i < WidgetTable.Shells.length; i++) {
        if (Shells[i] == null) {
          Shells[i] = ((Shell) (widget));
          return;
        }
      }
      Shell[] newShells = new Shell[WidgetTable.Shells.length + (GrowSize / 8)];
      System.arraycopy(Shells, 0, newShells, 0, WidgetTable.Shells.length);
      newShells[WidgetTable.Shells.length] = ((Shell) (widget));
      Shells = newShells;
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
    ArgList[1] = FreeSlot + 1;
    OS.XtSetValues(handle, ArgList, WidgetTable.ArgList.length / 2);
    int oldSlot = FreeSlot;
    FreeSlot = IndexTable[oldSlot];
    IndexTable[oldSlot] = -2;
    WidgetTable[oldSlot] = widget;
  }
}
