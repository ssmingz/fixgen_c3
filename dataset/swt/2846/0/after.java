class PlaceHold {
  int get_accHelp(int varChild, int pszHelp) {
    if (DEBUG) {
      print(this + ".IAccessible::get_accHelp");
    }
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    String osHelp = null;
    if (iaccessible != null) {
      code = iaccessible.get_accHelp(varChild, pszHelp);
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
    if (event.result.length() == 0) {
      return COM.S_FALSE;
    }
    setString(pszHelp, event.result);
    return COM.S_OK;
  }
}
