class PlaceHold {
  public int GetDomain(long[] aDomain) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 15 : 14), getAddress(), aDomain);
  }
}
