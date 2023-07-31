class PlaceHold {
  public int SetRelativeDescriptor(long fromFile, long relativeDesc) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 17, getAddress(), fromFile, relativeDesc);
  }
}
