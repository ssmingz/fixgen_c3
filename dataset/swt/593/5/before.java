class PlaceHold {
  public int ProfileExists(char[] profileName, boolean[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), profileName, retVal);
  }
}
