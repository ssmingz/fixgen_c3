class PlaceHold {
  public void setVersionFormat(String format) throws ConversionException {
    try {
      versionFormat = format;
      versionFormatPattern = Utils.getPattern(format);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + format, e);
    }
  }
}
