class COMObject {
  public COMObject(int[] argCounts) {
    int[] callbackAddresses = new int[argCounts.length];
    for (int i = 0, length = argCounts.length; i < length; i++) {
      if (Callbacks[i][argCounts[i]] == null) {
        Callbacks[i][argCounts[i]] =
            new Callback(this.getClass(), "callback" + i, argCounts[i] + 1, true);
      }
      callbackAddresses[i] = Callbacks[i][argCounts[i]].getAddress();
    }
    int pVtable = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, 4 * argCounts.length);
    COM.MoveMemory(pVtable, callbackAddresses, 4 * argCounts.length);
    ppVtable = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, 4);
    COM.MoveMemory(ppVtable, new int[] {pVtable}, 4);
    ObjectMap.put(new Integer(ppVtable), this);
  }
}
