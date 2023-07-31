class PlaceHold {
  int get_selectedCells(long ppCells, long pNSelectedCells) {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener = accessibleTableListeners.get(i);
      listener.getSelectedCells(event);
    }
    if (DEBUG) {
      print(
          (this + ".IAccessibleTable2::get_selectedCells() returning ")
              + (event.accessibles == null
                  ? "null"
                  : ("accessibles[" + event.accessibles.length) + "]"));
    }
    if ((event.accessibles == null) || (event.accessibles.length == 0)) {
      COM.MoveMemory(ppCells, new long[] {0}, PTR_SIZEOF);
      COM.MoveMemory(pNSelectedCells, new int[] {0}, 4);
      return COM.S_FALSE;
    }
    int length = event.accessibles.length;
    long pv = COM.CoTaskMemAlloc(length * OS.PTR_SIZEOF);
    int count = 0;
    for (int i = 0; i < length; i++) {
      Accessible accessible = event.accessibles[i];
      if (accessible != null) {
        accessible.AddRef();
        COM.MoveMemory(pv + (i * OS.PTR_SIZEOF), new long[] {accessible.getAddress()}, PTR_SIZEOF);
        count++;
      }
    }
    COM.MoveMemory(ppCells, new long[] {pv}, PTR_SIZEOF);
    COM.MoveMemory(pNSelectedCells, new int[] {count}, 4);
    return COM.S_OK;
  }
}
