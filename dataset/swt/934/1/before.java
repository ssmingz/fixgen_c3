class PlaceHold {
  public int Prompt(int aMessage, int aInitial, int aTitle, int aSavePassword, int retVal) {
    return XPCOM.VtblCall(
        super.LAST_METHOD_ID + 47, getAddress(), aMessage, aInitial, aTitle, aSavePassword, retVal);
  }
}
