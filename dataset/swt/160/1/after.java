class PlaceHold {
  public int DisableCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 13 : 12),
        getAddress(),
        capability,
        annotation);
  }
}
