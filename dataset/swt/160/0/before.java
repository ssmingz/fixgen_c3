class PlaceHold {
  public int EnableCapability(byte[] capability, int[] annotation) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 11 : 10), getAddress(), capability, annotation);
  }
}
