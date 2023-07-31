class PlaceHold {
  public Variant invoke(int dispIdMember) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, DISPATCH_METHOD, null, null, pVarResult);
    return result == COM.S_OK ? pVarResult : null;
  }
}
