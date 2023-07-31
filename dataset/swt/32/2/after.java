class PlaceHold {
  public Variant invoke(int dispIdMember, Variant[] rgvarg) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, DISPATCH_METHOD, rgvarg, null, pVarResult);
    return result == COM.S_OK ? pVarResult : null;
  }
}
