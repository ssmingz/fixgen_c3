class PlaceHold {
  int accDoDefaultAction(int varChild) {
    if (accessibleActionListeners.size() > 0) {
      VARIANT v = getVARIANT(varChild);
      if (v.vt != COM.VT_I4) {
        return COM.E_INVALIDARG;
      }
      if (v.lVal == COM.CHILDID_SELF) {
        return doAction(0);
      }
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    if (iaccessible != null) {
      code = iaccessible.accDoDefaultAction(varChild);
      if (code == COM.E_INVALIDARG) {
        code = COM.DISP_E_MEMBERNOTFOUND;
      }
    }
    return code;
  }
}
