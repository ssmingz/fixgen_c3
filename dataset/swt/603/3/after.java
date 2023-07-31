class PlaceHold {
  int get_accName(long varChild, long pszName) {
    if ((control != null) && control.isDisposed()) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.S_FALSE;
    String osName = null;
    if (iaccessible != null) {
      code = iaccessible.get_accName(varChild, pszName);
      if (code == COM.S_OK) {
        long[] pName = new long[1];
        COM.MoveMemory(pName, pszName, PTR_SIZEOF);
        int size = COM.SysStringByteLen(pName[0]);
        if (size > 0) {
          char[] buffer = new char[(size + 1) / 2];
          COM.MoveMemory(buffer, pName[0], size);
          osName = new String(buffer);
        }
      }
      if (code == COM.E_INVALIDARG) {
        code = COM.S_FALSE;
      }
      if ((accessibleListenersSize() == 0) && (!(control instanceof Text))) {
        if (DEBUG) {
          print(
              (((((this + ".IAccessible::get_accName(") + v.lVal) + ") returning name=") + osName)
                      + " from super")
                  + hresult(code));
        }
        return code;
      }
    }
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = osToChildID(v.lVal);
    event.result = osName;
    if (((control instanceof Text) && ((control.getStyle() & SWT.SEARCH) != 0))
        && (osName == null)) {
      event.result = ((Text) (control)).getMessage();
    }
    for (int i = 0; i < accessibleListenersSize(); i++) {
      AccessibleListener listener = accessibleListeners.get(i);
      listener.getName(event);
    }
    if (DEBUG) {
      print(
          ((((this + ".IAccessible::get_accName(") + v.lVal) + ") returning ") + event.result)
              + hresult(
                  event.result == null
                      ? code
                      : event.result.length() == 0 ? COM.S_FALSE : COM.S_OK));
    }
    if (event.result == null) {
      return code;
    }
    if (event.result.length() == 0) {
      return COM.S_FALSE;
    }
    setString(pszName, event.result);
    return COM.S_OK;
  }
}
