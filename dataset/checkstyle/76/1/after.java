class PlaceHold {
  public void setOffCommentFormat(String format) throws ConversionException {
    offRegexp = Utils.createPattern(format);
  }
}
