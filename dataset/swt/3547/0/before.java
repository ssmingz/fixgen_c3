class PlaceHold {
  public int PostMessageMoz(long message, long targetOrigin, long cx) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 33, getAddress(), message, targetOrigin, cx);
  }
}
