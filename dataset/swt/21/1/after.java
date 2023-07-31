class PlaceHold {
  public int AppendRelativePath(long relativeFilePath) {
    if (IsXULRunner17) {
      return super.AppendRelativePath(relativeFilePath);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 10, getAddress(), relativeFilePath);
  }
}
