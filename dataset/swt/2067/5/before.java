class PlaceHold {
  public TYPEATTR getTypeInfoAttributes() {
    if (objITypeInfo == null) {
      return null;
    }
    int[] ppTypeAttr = new int[1];
    int rc = objITypeInfo.GetTypeAttr(ppTypeAttr);
    if (rc != OLE.S_OK) {
      return null;
    }
    TYPEATTR typeattr = new TYPEATTR();
    COM.MoveMemory(typeattr, ppTypeAttr[0], sizeof);
    objITypeInfo.ReleaseTypeAttr(ppTypeAttr[0]);
    return typeattr;
  }
}
