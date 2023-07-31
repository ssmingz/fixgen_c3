class PlaceHold {
  public int RevertCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 12 : 11),
        getAddress(),
        capability,
        annotation);
  }
}
