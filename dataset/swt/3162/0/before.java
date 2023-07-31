class PlaceHold {
  public int GetDocument(int[] aDocument) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), aDocument);
  }
}
