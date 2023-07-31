class PlaceHold {
  public int InitWithFile(long aFile) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 3, getAddress(), aFile);
  }
}
