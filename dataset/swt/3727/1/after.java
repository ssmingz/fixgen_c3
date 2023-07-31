class PlaceHold {
  public int GetHasCertificate(int[] aHasCertificate) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 18 : 17), getAddress(), aHasCertificate);
  }
}
