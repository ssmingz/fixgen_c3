class PlaceHold {
  public int InitWithNativePath(int filePath) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), filePath);
  }
}
