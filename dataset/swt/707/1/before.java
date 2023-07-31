class PlaceHold {
  public int GetPrettyName(int aPrettyName) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (Is8 ? 20 : 19), getAddress(), aPrettyName);
  }
}
