class PlaceHold {
  public int SetDomain(long aDomain) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 16 : 15), getAddress(), aDomain);
  }
}
