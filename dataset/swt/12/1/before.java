class PlaceHold {
  int get_accChild(int varChild, int ppdispChild) {
    VARIANT v = getVARIANT(varChild);
    if (v.vt != COM.VT_I4) {
      return COM.E_INVALIDARG;
    }
    int code = COM.S_FALSE;
    if (iaccessible != null) {
      code = iaccessible.get_accChild(varChild, ppdispChild);
      if (code == COM.E_INVALIDARG) {
        code = COM.S_FALSE;
      }
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = osToChildID(v.lVal);
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getChild(event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      accessible.AddRef();
      COM.MoveMemory(ppdispChild, new int[] {accessible.getAddress()}, PTR_SIZEOF);
      return COM.S_OK;
    }
    return code;
  }
}
