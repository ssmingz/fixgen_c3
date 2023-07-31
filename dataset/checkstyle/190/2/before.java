class PlaceHold {
  public void setCheckFormat(String format) throws ConversionException {
    try {
      checkRegexp = Utils.getPattern(format);
      checkFormat = format;
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
