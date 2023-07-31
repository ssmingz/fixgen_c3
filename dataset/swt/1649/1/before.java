class PlaceHold {
  public OlePropertyDescription getPropertyDescription(int index) {
    if (objITypeInfo == null) {
      return null;
    }
    int[] ppVarDesc = new int[1];
    int rc = objITypeInfo.GetVarDesc(index, ppVarDesc);
    if (rc != OLE.S_OK) {
      return null;
    }
    VARDESC vardesc = new VARDESC();
    COM.MoveMemory(vardesc, ppVarDesc[0], sizeof);
    OlePropertyDescription data = new OlePropertyDescription();
    data.id = vardesc.memid;
    data.name = getName(vardesc.memid);
    data.type = vardesc.elemdescVar_tdesc_vt;
    if (data.type == OLE.VT_PTR) {
      short[] vt = new short[1];
      COM.MoveMemory(vt, vardesc.elemdescVar_tdesc_union + 4, 2);
      data.type = vt[0];
    }
    data.flags = vardesc.wVarFlags;
    data.kind = vardesc.varkind;
    data.description = getDocumentation(vardesc.memid);
    data.helpFile = getHelpFile(vardesc.memid);
    objITypeInfo.ReleaseVarDesc(ppVarDesc[0]);
    return data;
  }
}
