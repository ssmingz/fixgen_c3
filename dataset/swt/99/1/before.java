class PlaceHold {
  public int GetAttributeNodeNS(long namespaceURI, long localName, long[] _retval) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + 12, getAddress(), namespaceURI, localName, _retval);
  }
}
