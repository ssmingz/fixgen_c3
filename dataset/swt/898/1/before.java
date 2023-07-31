class PlaceHold {
  public int SetAttributeNode(long newAttr, long[] _retval) {
    return XPCOM.VtblCall(nsIDOMNode.LAST_METHOD_ID + 6, getAddress(), newAttr, _retval);
  }
}
