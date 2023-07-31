class PlaceHold {
  public int CanEnableCapability(byte[] capability, int _retval) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 8 : 7), getAddress(), capability, _retval);
  }
}
