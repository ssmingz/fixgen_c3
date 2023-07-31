class PlaceHold {
  int get_accParent(int ppdispParent) {
    int code = COM.DISP_E_MEMBERNOTFOUND;
    if (iaccessible != null) {
      code = iaccessible.get_accParent(ppdispParent);
    }
    if (parent != null) {
      parent.AddRef();
      setPtrVARIANT(ppdispParent, VT_DISPATCH, parent.getAddress());
      code = COM.S_OK;
    }
    return code;
  }
}
