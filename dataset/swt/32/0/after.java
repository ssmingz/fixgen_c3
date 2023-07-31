class PlaceHold {
  public Variant invoke(int dispIdMember, Variant[] rgvarg, int[] rgdispidNamedArgs) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, DISPATCH_METHOD, rgvarg, rgdispidNamedArgs, pVarResult);
    return result == COM.S_OK ? pVarResult : null;
  }
}
