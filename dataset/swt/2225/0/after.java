class PlaceHold {
  public int GetDocument(long[] aDocument) {
    return XPCOM.VtblCall(this.getGetterIndex("document"), getAddress(), aDocument);
  }
}
