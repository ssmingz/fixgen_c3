class PlaceHold {
  public int SetAttributeNS(long namespaceURI, long qualifiedName, long value) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + 10, getAddress(), namespaceURI, qualifiedName, value);
  }
}
