class PlaceHold {
  public int CopyTo(int newParentDir, int newName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, getAddress(), newParentDir, newName);
  }
}
