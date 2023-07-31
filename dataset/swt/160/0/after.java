class PlaceHold {
  public int EnableCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 11 : 10),
        getAddress(),
        capability,
        annotation);
  }
}
