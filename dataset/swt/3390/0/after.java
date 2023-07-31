class PlaceHold {
  public int SetRelativeDescriptor(int fromFile, int relativeDesc) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 17, getAddress(), fromFile, relativeDesc);
  }
}
