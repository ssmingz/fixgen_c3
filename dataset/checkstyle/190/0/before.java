class PlaceHold {
  public void setAuthorFormat(String format) throws ConversionException {
    try {
      authorFormat = format;
      authorFormatPattern = Utils.getPattern(format);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
