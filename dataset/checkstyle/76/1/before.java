class PlaceHold {
  public void setOffCommentFormat(String format) throws ConversionException {
    try {
      offRegexp = Utils.getPattern(format);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
