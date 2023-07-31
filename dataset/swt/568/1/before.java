class PlaceHold {
  int get_accHelp(int variant, int pszHelp) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = new VARIANT();
    COM.MoveMemory(v, variant, sizeof);
    if ((v.vt & 0xffff) != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    String osHelp = null;
    int code = iaccessible.get_accHelp(variant, pszHelp);
    if (code == COM.E_INVALIDARG) {
      code = COM.S_FALSE;
    }
    if (accessibleListeners.size() == 0) {
      return code;
    }
    if (code == COM.S_OK) {
      int[] pHelp = new int[1];
      COM.MoveMemory(pHelp, pszHelp, PTR_SIZEOF);
      int size = COM.SysStringByteLen(pHelp[0]);
      if (size > 0) {
        char[] buffer = new char[(size + 1) / 2];
        COM.MoveMemory(buffer, pHelp[0], size);
        osHelp = new String(buffer);
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osHelp;
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getHelp(event);
    }
    if (event.result == null) {
      return code;
    }
    char[] data = (event.result + "\u0000").toCharArray();
    int ptr = COM.SysAllocString(data);
    COM.MoveMemory(pszHelp, new int[] {ptr}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
