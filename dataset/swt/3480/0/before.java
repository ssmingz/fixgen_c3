class PlaceHold {
  public int EnableCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + 11, getAddress(), capability, annotation);
  }
}
