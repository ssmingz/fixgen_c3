class PlaceHold {
  public void setTagFormat(String format) throws ConversionException {
    try {
      tagFormat = format;
      tagFormatRE = Utils.getPattern(format);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
