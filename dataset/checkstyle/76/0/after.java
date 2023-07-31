class PlaceHold {
  public void setOnCommentFormat(String format) throws ConversionException {
    onRegexp = Utils.createPattern(format);
  }
}
