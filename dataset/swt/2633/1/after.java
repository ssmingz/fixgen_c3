class PlaceHold {
  public int InitWithNativePath(int filePath) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 2, getAddress(), filePath);
  }
}
