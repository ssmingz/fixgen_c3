class PlaceHold {
  public int AppendRelativePath(int relativeFilePath) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 10, getAddress(), relativeFilePath);
  }
}
