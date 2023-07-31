class PlaceHold {
  public int AppendRelativeNativePath(long relativeFilePath) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 11, getAddress(), relativeFilePath);
  }
}
