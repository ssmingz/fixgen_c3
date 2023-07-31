class PlaceHold {
  public int MoveToNative(int newParentDir, int newName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 14, getAddress(), newParentDir, newName);
  }
}
