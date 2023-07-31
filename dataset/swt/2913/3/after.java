class PlaceHold {
  public int CopyToNative(int newParentDir, int newName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, getAddress(), newParentDir, newName);
  }
}
