class PlaceHold {
  public int Prompt(long aMessage, long aInitial, long _retval) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 33 : 30),
        getAddress(),
        aMessage,
        aInitial,
        _retval);
  }
}
