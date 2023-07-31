class PlaceHold {
  public int GetDocument(int[] aDocument) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, getAddress(), aDocument);
  }
}
