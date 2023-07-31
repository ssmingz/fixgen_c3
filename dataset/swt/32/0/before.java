class PlaceHold {
  public Variant invoke(int dispIdMember, Variant[] rgvarg, int[] rgdispidNamedArgs) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, rgvarg, rgdispidNamedArgs, pVarResult);
    if (result == COM.S_OK) {
      return pVarResult;
    }
    return null;
  }
}
