class PlaceHold {
  public int SetCanEnableCapability(byte[] capability, short canEnable) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 9, getAddress(), capability, canEnable);
  }
}
