class PlaceHold {
  public int IsCapabilityEnabled(byte[] capability, long annotation, int[] _retval) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner17 ? 8 : IsXULRunner10 ? 10 : 9),
        getAddress(),
        capability,
        annotation,
        _retval);
  }
}
