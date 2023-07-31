class PlaceHold {
  public int CheckMayLoad(long uri, int report) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 22 : 21), getAddress(), uri, report);
  }
}
