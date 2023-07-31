class PlaceHold {
  public void setVersionFormat(String aFormat) throws ConversionException {
    try {
      mVersionFormat = aFormat;
      mVersionFormatRE = Utils.getRE(aFormat);
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
