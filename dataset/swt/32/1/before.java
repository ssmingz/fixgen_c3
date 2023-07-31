class PlaceHold {
  public Variant invoke(int dispIdMember) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, null, null, pVarResult);
    if (result == COM.S_OK) {
      return pVarResult;
    }
    return null;
  }
}
