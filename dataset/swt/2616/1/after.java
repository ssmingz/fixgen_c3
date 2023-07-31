class PlaceHold {
  public int GetBusyFlags(int[] busyFlags) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 44, getAddress(), busyFlags);
  }
}
