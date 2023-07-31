class PlaceHold {
  public void setOnCommentFormat(String format) throws ConversionException {
    try {
      onRegexp = Utils.getPattern(format);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
