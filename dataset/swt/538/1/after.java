class PlaceHold {
  public int InitWithFile(long aFile) {
    if (IsXULRunner17) {
      return super.InitWithFile(aFile);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 3, getAddress(), aFile);
  }
}
