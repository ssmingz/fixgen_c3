class PlaceHold {
  public int GetDocument(int[] aDocument) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 3 : 1), getAddress(), aDocument);
  }
}
