class PlaceHold {
  int get_accValue(int variant, int pszValue) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = new VARIANT();
    COM.MoveMemory(v, variant, sizeof);
    if ((v.vt & 0xffff) != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    String osValue = null;
    int code = iaccessible.get_accValue(variant, pszValue);
    if (code == COM.E_INVALIDARG) {
      code = COM.S_FALSE;
    }
    if (accessibleControlListeners.size() == 0) {
      return code;
    }
    if (code == COM.S_OK) {
      int[] pValue = new int[1];
      COM.MoveMemory(pValue, pszValue, PTR_SIZEOF);
      int size = COM.SysStringByteLen(pValue[0]);
      if (size > 0) {
        char[] buffer = new char[(size + 1) / 2];
        COM.MoveMemory(buffer, pValue[0], size);
        osValue = new String(buffer);
      }
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osValue;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getValue(event);
    }
    if (event.result == null) {
      return code;
    }
    char[] data = (event.result + "\u0000").toCharArray();
    int ptr = COM.SysAllocString(data);
    COM.MoveMemory(pszValue, new int[] {ptr}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
