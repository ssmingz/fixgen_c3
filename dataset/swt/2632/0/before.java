class PlaceHold {
  public int CanEnableCapability(byte[] capability, int _retval) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 8, getAddress(), capability, _retval);
  }
}
