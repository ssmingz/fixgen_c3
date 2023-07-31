class PlaceHold {
  public int GetElementsByTagNameNS(long namespaceURI, long localName, long[] _retval) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + (IsXULRunner17 ? 17 : 14),
        getAddress(),
        namespaceURI,
        localName,
        _retval);
  }
}
