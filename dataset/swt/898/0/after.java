class PlaceHold {
  public int SetAttributeNS(long namespaceURI, long qualifiedName, long value) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + (IsXULRunner17 ? 6 : 10),
        getAddress(),
        namespaceURI,
        qualifiedName,
        value);
  }
}
