class PlaceHold {
  int get_accKeyboardShortcut(int variant, int pszKeyboardShortcut) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = new VARIANT();
    COM.MoveMemory(v, variant, sizeof);
    if ((v.vt & 0xffff) != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    String osKeyboardShortcut = null;
    int code = iaccessible.get_accKeyboardShortcut(variant, pszKeyboardShortcut);
    if (code == COM.E_INVALIDARG) {
      code = COM.S_FALSE;
    }
    if (accessibleListeners.size() == 0) {
      return code;
    }
    if (code == COM.S_OK) {
      int[] pKeyboardShortcut = new int[1];
      COM.MoveMemory(pKeyboardShortcut, pszKeyboardShortcut, PTR_SIZEOF);
      int size = COM.SysStringByteLen(pKeyboardShortcut[0]);
      if (size > 0) {
        char[] buffer = new char[(size + 1) / 2];
        COM.MoveMemory(buffer, pKeyboardShortcut[0], size);
        osKeyboardShortcut = new String(buffer);
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osKeyboardShortcut;
    for (int i = 0; i < accessibleListeners.size(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getKeyboardShortcut(event);
    }
    if (event.result == null) {
      return code;
    }
    char[] data = (event.result + "\u0000").toCharArray();
    int ptr = COM.SysAllocString(data);
    COM.MoveMemory(pszKeyboardShortcut, new int[] {ptr}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
