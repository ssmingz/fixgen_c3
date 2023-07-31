class PlaceHold {
  int QueryService(int guidService, int riid, int ppvObject) {
    if ((riid == 0) || (ppvObject == 0)) {
      return COM.E_INVALIDARG;
    }
    GUID guid = new GUID();
    COM.MoveMemory(guid, riid, sizeof);
    if (COM.IsEqualGUID(guid, IIDIInternetSecurityManager)) {
      COM.MoveMemory(ppvObject, new int[] {iInternetSecurityManager.getAddress()}, PTR_SIZEOF);
      AddRef();
      return COM.S_OK;
    }
    COM.MoveMemory(ppvObject, new int[] {0}, PTR_SIZEOF);
    return COM.E_NOINTERFACE;
  }
}
