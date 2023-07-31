class PlaceHold {
  public int GetOrigin(int[] aOrigin) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 17 : 16), getAddress(), aOrigin);
  }
}
