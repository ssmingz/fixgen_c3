class PlaceHold {
  public int GetURI(long[] aURI) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 10 : IsXULRunner10 ? 14 : 13),
        getAddress(),
        aURI);
  }
}
