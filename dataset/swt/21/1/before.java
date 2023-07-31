class PlaceHold {
  public int AppendRelativePath(long relativeFilePath) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 10, getAddress(), relativeFilePath);
  }
}
