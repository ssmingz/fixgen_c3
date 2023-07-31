class PlaceHold {
  protected int QueryInterface(int riid, int ppvObject) {
    if ((riid == 0) || (ppvObject == 0)) {
      return COM.E_NOINTERFACE;
    }
    GUID guid = new GUID();
    COM.MoveMemory(guid, riid, sizeof);
    if (COM.IsEqualGUID(guid, IIDIUnknown)) {
      COM.MoveMemory(ppvObject, new int[] {iUnknown.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIAdviseSink)) {
      COM.MoveMemory(ppvObject, new int[] {iAdviseSink.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIOleClientSite)) {
      COM.MoveMemory(ppvObject, new int[] {iOleClientSite.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIOleInPlaceSite)) {
      COM.MoveMemory(ppvObject, new int[] {iOleInPlaceSite.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    COM.MoveMemory(ppvObject, new int[] {0}, 4);
    return COM.E_NOINTERFACE;
  }
}
