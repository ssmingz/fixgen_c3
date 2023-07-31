class PlaceHold {
  public int GetPrettyName(long aPrettyName) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 16 : IsXULRunner10 ? 20 : 19),
        getAddress(),
        aPrettyName);
  }
}
