class PlaceHold {
  public void setText(boolean plainText) {
    optionalAttrs.put(TEXT, plainText ? Boolean.TRUE : Boolean.FALSE);
    this.plainText = plainText;
  }
}
