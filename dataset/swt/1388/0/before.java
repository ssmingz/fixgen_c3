class PlaceHold {
  public int DisableCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + 13, getAddress(), capability, annotation);
  }
}
