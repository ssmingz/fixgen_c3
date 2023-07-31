class PlaceHold {
  public int CopyToNative(int newParentDir, int newName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 10, getAddress(), newParentDir, newName);
  }
}
