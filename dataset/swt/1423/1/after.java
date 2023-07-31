class PlaceHold {
  public int GetOrigin(long[] aOrigin) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 13 : IsXULRunner10 ? 17 : 16),
        getAddress(),
        aOrigin);
  }
}
