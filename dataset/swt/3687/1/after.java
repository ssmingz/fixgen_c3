class PlaceHold {
  public int CheckMayLoad(long uri, int report) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 19 : IsXULRunner10 ? 22 : 21),
        getAddress(),
        uri,
        report);
  }
}
