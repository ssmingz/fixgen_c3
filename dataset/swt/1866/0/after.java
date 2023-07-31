class PlaceHold {
  int Clone(int ppEnum) {
    if (accessibleControlListeners.size() == 0) {
      int[] ppvObject = new int[1];
      int code = iaccessible.QueryInterface(IIDIEnumVARIANT, ppvObject);
      if (code != COM.S_OK) {
        return code;
      }
      IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
      int[] pEnum = new int[1];
      code = ienumvariant.Clone(pEnum);
      ienumvariant.Release();
      COM.MoveMemory(ppEnum, pEnum, PTR_SIZEOF);
      return code;
    }
    if (ppEnum == 0) {
      return COM.E_INVALIDARG;
    }
    COM.MoveMemory(ppEnum, new int[] {objIEnumVARIANT.getAddress()}, PTR_SIZEOF);
    AddRef();
    return COM.S_OK;
  }
}
