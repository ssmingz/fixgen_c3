class PlaceHold {
  public int SetAttribute(long name, long value) {
    return XPCOM.VtblCall(nsIDOMNode.LAST_METHOD_ID + 3, getAddress(), name, value);
  }
}
