class PlaceHold {
  public int GetAttributeNode(long name, long[] _retval) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + (IsXULRunner17 ? 11 : 5), getAddress(), name, _retval);
  }
}
