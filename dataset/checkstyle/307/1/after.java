class PlaceHold {
  public void setAuthorFormat(String aFormat) throws ConversionException {
    try {
      mAuthorFormat = aFormat;
      mAuthorFormatPattern = Utils.getPattern(aFormat);
    } catch (PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
