class PlaceHold {
  public int InitWithPath(int filePath) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 1, getAddress(), filePath);
  }
}
