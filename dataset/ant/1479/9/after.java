class PlaceHold {
  public void setStatic(boolean staticParser) {
    optionalAttrs.put(STATIC, staticParser ? Boolean.TRUE : Boolean.FALSE);
  }
}
