class PlaceHold {
  public int AppendRelativeNativePath(long relativeFilePath) {
    if (IsXULRunner17) {
      return super.AppendRelativeNativePath(relativeFilePath);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 11, getAddress(), relativeFilePath);
  }
}
