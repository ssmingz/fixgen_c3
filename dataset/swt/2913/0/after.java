class PlaceHold {
  public int MoveTo(int newParentDir, int newName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, getAddress(), newParentDir, newName);
  }
}
