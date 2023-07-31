class PlaceHold {
  int get_selectedColumns(long ppSelectedColumns, long pNColumns) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener = accessibleTableListeners.get(i);
      listener.getSelectedColumns(event);
    }
    int count = (event.selected == null) ? 0 : event.selected.length;
    if (DEBUG) {
      print(
          (this + ".IAccessibleTable2::get_selectedColumns() returning ")
              + (count == 0 ? "null" : ("selected[" + count) + "]"));
    }
    if (count == 0) {
      COM.MoveMemory(ppSelectedColumns, new long[] {0}, PTR_SIZEOF);
      COM.MoveMemory(pNColumns, new int[] {0}, 4);
      return COM.S_FALSE;
    }
    long pv = COM.CoTaskMemAlloc(count * 4);
    COM.MoveMemory(pv, event.selected, count * 4);
    COM.MoveMemory(ppSelectedColumns, new long[] {pv}, PTR_SIZEOF);
    COM.MoveMemory(pNColumns, new int[] {count}, 4);
    return COM.S_OK;
  }
}
