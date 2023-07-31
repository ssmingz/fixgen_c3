class PlaceHold {
  public int GetFingerprint(int aFingerprint) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 19 : 18), getAddress(), aFingerprint);
  }
}
