class XPCOMObject {
  public XPCOMObject(int[] argCounts) {
    int[] callbackAddresses = new int[argCounts.length];
    synchronized (Callbacks) {
      for (int i = 0, length = argCounts.length; i < length; i++) {
        if (Callbacks[i][argCounts[i]] == null) {
          Callbacks[i][argCounts[i]] =
              new Callback(
                  getClass(), "callback" + i, argCounts[i] + 1, true, XPCOM.NS_ERROR_FAILURE);
        }
        callbackAddresses[i] = Callbacks[i][argCounts[i]].getAddress();
        if (callbackAddresses[i] == 0) {
          SWT.error(ERROR_NO_MORE_CALLBACKS);
        }
      }
    }
    int pVtable = C.malloc(C.PTR_SIZEOF * argCounts.length);
    XPCOM.memmove(pVtable, callbackAddresses, C.PTR_SIZEOF * argCounts.length);
    ppVtable = C.malloc(PTR_SIZEOF);
    XPCOM.memmove(ppVtable, new int[] {pVtable}, PTR_SIZEOF);
    ObjectMap.put(new LONG(ppVtable), this);
  }
}
