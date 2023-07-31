class PlaceHold {
  int get_accParent(int ppdispParent) {
    int code = COM.DISP_E_MEMBERNOTFOUND;
    if (iaccessible != null) {
      code = iaccessible.get_accParent(ppdispParent);
    }
    if (parent != null) {
      parent.AddRef();
      COM.MoveMemory(ppdispParent, new int[] {parent.getAddress()}, PTR_SIZEOF);
      code = COM.S_OK;
    }
    return code;
  }
}
