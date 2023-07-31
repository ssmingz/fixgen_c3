class PlaceHold {
  public int GetRelativeDescriptor(int fromFile, int _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 16, getAddress(), fromFile, _retval);
  }
}
