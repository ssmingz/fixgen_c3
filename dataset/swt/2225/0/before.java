class PlaceHold {
  public int GetDocument(long[] aDocument) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10() || IsXULRunner24() ? 3 : 1),
        getAddress(),
        aDocument);
  }
}
