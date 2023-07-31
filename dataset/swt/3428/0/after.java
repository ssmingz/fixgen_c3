class PlaceHold {
  public int GetHashValue(int[] aHashValue) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 4 : 3), getAddress(), aHashValue);
  }
}
