class PlaceHold {
  public int GetCertificate(long[] aCertificate) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 24 : 23), getAddress(), aCertificate);
  }
}
