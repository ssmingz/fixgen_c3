class PlaceHold {
  public void setVersionFormat(String aFormat) throws ConversionException {
    try {
      mVersionFormat = aFormat;
      mVersionFormatPattern = Utils.getPattern(aFormat);
    } catch (PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
