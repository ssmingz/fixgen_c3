class PlaceHold {
  public int SetOnpageshow(long cx, long aOnpageshow) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 121 : 117), getAddress(), cx, aOnpageshow);
  }
}
