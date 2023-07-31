class PlaceHold {
  public int InitWithPath(int filePath) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), filePath);
  }
}
