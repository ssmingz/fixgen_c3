class PlaceHold {
  public int GetDiskSpaceAvailable(long[] aDiskSpaceAvailable) {
    if (IsXULRunner17) {
      return super.GetDiskSpaceAvailable(aDiskSpaceAvailable);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 9, getAddress(), aDiskSpaceAvailable);
  }
}
