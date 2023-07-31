class PlaceHold {
  protected int QueryInterface(int riid, int ppvObject) {
    int result = super.QueryInterface(riid, ppvObject);
    if (result == COM.S_OK) {
      return result;
    }
    if ((riid == 0) || (ppvObject == 0)) {
      return COM.E_INVALIDARG;
    }
    GUID guid = new GUID();
    COM.MoveMemory(guid, riid, sizeof);
    if (COM.IsEqualGUID(guid, IIDIDocHostUIHandler)) {
      COM.MoveMemory(ppvObject, new int[] {iDocHostUIHandler.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    COM.MoveMemory(ppvObject, new int[] {0}, 4);
    return COM.E_NOINTERFACE;
  }
}
