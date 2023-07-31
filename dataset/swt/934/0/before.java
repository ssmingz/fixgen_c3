class PlaceHold {
  public int OpenDialog(int url, int name, int options, int aExtraArgument, int[] retVal) {
    return XPCOM.VtblCall(
        super.LAST_METHOD_ID + 61, getAddress(), url, name, options, aExtraArgument, retVal);
  }
}
