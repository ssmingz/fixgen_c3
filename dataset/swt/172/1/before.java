class PlaceHold {
  int get_accName(int variant, int pszName) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = new VARIANT();
    COM.MoveMemory(v, variant, sizeof);
    if ((v.vt & 0xffff) != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    String osName = null;
    int code = iaccessible.get_accName(variant, pszName);
    if (code == COM.E_INVALIDARG) {
      code = COM.S_FALSE;
    }
    if (accessibleListeners.size() == 0) {
      return code;
    }
    if (code == COM.S_OK) {
      int[] pName = new int[1];
      COM.MoveMemory(pName, pszName, PTR_SIZEOF);
      int size = COM.SysStringByteLen(pName[0]);
      if (size > 0) {
        char[] buffer = new char[(size + 1) / 2];
        COM.MoveMemory(buffer, pName[0], size);
        osName = new String(buffer);
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osName;
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getName(event);
    }
    if (event.result == null) {
      return code;
    }
    char[] data = (event.result + "\u0000").toCharArray();
    int ptr = COM.SysAllocString(data);
    COM.MoveMemory(pszName, new int[] {ptr}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
