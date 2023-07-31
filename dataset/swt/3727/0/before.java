class PlaceHold {
  public int GetCertificate(int[] aCertificate) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 24 : 23), getAddress(), aCertificate);
  }
}
