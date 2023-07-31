class PlaceHold {
  public int MoveToNative(int newParentDir, int newName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, getAddress(), newParentDir, newName);
  }
}
