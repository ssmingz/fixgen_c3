class PlaceHold {
  int put_accValue(long varChild, long szValue) {
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    if ((v.lVal == COM.CHILDID_SELF) && (accessibleEditableTextListenersSize() > 0)) {
      AccessibleEditableTextEvent event = new AccessibleEditableTextEvent(this);
      event.start = 0;
      event.end = getCharacterCount();
      if (event.end >= 0) {
        int size = COM.SysStringByteLen(szValue);
        char[] buffer = new char[(size + 1) / 2];
        OS.MoveMemory(buffer, szValue, size);
        event.string = new String(buffer);
        for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
          AccessibleEditableTextListener listener =
              ((AccessibleEditableTextListener) (accessibleEditableTextListeners.elementAt(i)));
          listener.replaceText(event);
        }
        if ((event.result != null) && event.result.equals(OK)) {
          code = COM.S_OK;
        }
        if (DEBUG) {
          print(
              (((((this + ".IAccessible::put_accValue(") + v.lVal) + ", \"") + event.string)
                      + "\") returning ")
                  + hresult(code));
        }
      }
    }
    if ((code != COM.S_OK) && (iaccessible != null)) {
      code = iaccessible.put_accValue(varChild, szValue);
      if (code == COM.E_INVALIDARG) {
        code = COM.DISP_E_MEMBERNOTFOUND;
      }
      if (DEBUG) {
        print(
            ((((this + ".IAccessible::put_accValue(") + v.lVal) + ") returning ") + hresult(code))
                + " from proxy");
      }
    }
    return code;
  }
}
