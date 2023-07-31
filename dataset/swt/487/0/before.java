class PlaceHold {
  public int GetRelativeDescriptor(long fromFile, long _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 16, getAddress(), fromFile, _retval);
  }
}
