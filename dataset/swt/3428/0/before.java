class PlaceHold {
  public int GetHashValue(int[] aHashValue) {
    return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + (Is8 ? 4 : 3), getAddress(), aHashValue);
  }
}
