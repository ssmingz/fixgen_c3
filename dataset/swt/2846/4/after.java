class PlaceHold {
  int get_accDefaultAction(int varChild, int pszDefaultAction) {
    if (DEBUG) {
      print(this + ".IAccessible::get_accDefaultAction");
    }
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    String osDefaultAction = null;
    if (iaccessible != null) {
      code = iaccessible.get_accDefaultAction(varChild, pszDefaultAction);
      if (code == COM.E_INVALIDARG) {
        code = COM.S_FALSE;
      }
      if (accessibleControlListeners.size() == 0) {
        return code;
      }
      if (code == COM.S_OK) {
        int[] pDefaultAction = new int[1];
        COM.MoveMemory(pDefaultAction, pszDefaultAction, PTR_SIZEOF);
        int size = COM.SysStringByteLen(pDefaultAction[0]);
        if (size > 0) {
          char[] buffer = new char[(size + 1) / 2];
          COM.MoveMemory(buffer, pDefaultAction[0], size);
          osDefaultAction = new String(buffer);
        }
      }
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osDefaultAction;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getDefaultAction(event);
    }
    if (((event.result == null) || (event.result.length() == 0)) && (v.lVal == COM.CHILDID_SELF)) {
      code = get_name(0, pszDefaultAction);
    }
    if (event.result == null) {
      return code;
    }
    if (event.result.length() == 0) {
      return COM.S_FALSE;
    }
    setString(pszDefaultAction, event.result);
    return COM.S_OK;
  }
}
