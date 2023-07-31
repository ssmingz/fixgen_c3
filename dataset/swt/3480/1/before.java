class PlaceHold {
  public int RevertCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + 12, getAddress(), capability, annotation);
  }
}
