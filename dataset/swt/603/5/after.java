class PlaceHold {
  int get_accKeyboardShortcut(long varChild, long pszKeyboardShortcut) {
    if (DEBUG) {
      print(this + ".IAccessible::get_accKeyboardShortcut");
    }
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    String osKeyboardShortcut = null;
    if (iaccessible != null) {
      code = iaccessible.get_accKeyboardShortcut(varChild, pszKeyboardShortcut);
      if (code == COM.E_INVALIDARG) {
        code = COM.S_FALSE;
      }
      if ((accessibleListenersSize() == 0) && (!(control instanceof TabFolder))) {
        return code;
      }
      if (code == COM.S_OK) {
        long[] pKeyboardShortcut = new long[1];
        COM.MoveMemory(pKeyboardShortcut, pszKeyboardShortcut, PTR_SIZEOF);
        int size = COM.SysStringByteLen(pKeyboardShortcut[0]);
        if (size > 0) {
          char[] buffer = new char[(size + 1) / 2];
          COM.MoveMemory(buffer, pKeyboardShortcut[0], size);
          osKeyboardShortcut = new String(buffer);
        }
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osKeyboardShortcut;
    if ((v.lVal == COM.CHILDID_SELF) && (control instanceof TabFolder)) {
      event.result = SWT.getMessage("SWT_SwitchPage_Shortcut");
    }
    for (int i = 0; i < accessibleListenersSize(); i++) {
      AccessibleListener listener = accessibleListeners.get(i);
      listener.getKeyboardShortcut(event);
    }
    if (event.result == null) {
      return code;
    }
    if (event.result.length() == 0) {
      return COM.S_FALSE;
    }
    setString(pszKeyboardShortcut, event.result);
    return COM.S_OK;
  }
}
