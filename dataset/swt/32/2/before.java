class PlaceHold {
  public Variant invoke(int dispIdMember, Variant[] rgvarg) {
    Variant pVarResult = new Variant();
    int result = invoke(dispIdMember, rgvarg, null, pVarResult);
    if (result == COM.S_OK) {
      return pVarResult;
    }
    return null;
  }
}
