class PlaceHold {
  public void setText(boolean plainText) {
    optionalAttrs.put(TEXT, new Boolean(plainText));
    this.plainText = plainText;
  }
}
