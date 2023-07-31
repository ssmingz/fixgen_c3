class PlaceHold {
  int QueryInterface(int arg1, int arg2) {
    GUID guid = new GUID();
    COM.MoveMemory(guid, arg1, sizeof);
    if (COM.IsEqualGUID(guid, IIDIUnknown)) {
      COM.MoveMemory(arg2, new int[] {objIAccessible.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIDispatch)) {
      COM.MoveMemory(arg2, new int[] {objIAccessible.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIAccessible)) {
      COM.MoveMemory(arg2, new int[] {objIAccessible.getAddress()}, 4);
      AddRef();
      return COM.S_OK;
    }
    if (COM.IsEqualGUID(guid, IIDIEnumVARIANT)) {
      COM.MoveMemory(arg2, new int[] {objIEnumVARIANT.getAddress()}, 4);
      AddRef();
      enumIndex = 0;
      return COM.S_OK;
    }
    int[] ppvObject = new int[1];
    int result = iaccessible.QueryInterface(guid, ppvObject);
    COM.MoveMemory(arg2, ppvObject, 4);
    return result;
  }
}
