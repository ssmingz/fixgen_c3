class PlaceHold {
  public int GetDiskSpaceAvailable(long[] aDiskSpaceAvailable) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 9, getAddress(), aDiskSpaceAvailable);
  }
}
