class PlaceHold {
  public int SetCanEnableCapability(byte[] capability, short canEnable) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 9 : 8), getAddress(), capability, canEnable);
  }
}
