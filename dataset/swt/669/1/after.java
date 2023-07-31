class PlaceHold {
  public int SetAttribute(long name, long value) {
    return XPCOM.VtblCall(
        nsIDOMNode.LAST_METHOD_ID + (IsXULRunner17 ? 5 : 3), getAddress(), name, value);
  }
}
