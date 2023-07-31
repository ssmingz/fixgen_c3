class PlaceHold {
  public int EnableCapability(byte[] capability, long[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 9 : IsXULRunner10 ? 11 : 10),
        getAddress(),
        capability,
        annotation);
  }
}
