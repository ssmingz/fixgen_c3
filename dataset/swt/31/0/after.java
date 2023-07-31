class PlaceHold {
  public int GetTimeStamp(int[] aTimeStamp) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aTimeStamp);
  }
}
