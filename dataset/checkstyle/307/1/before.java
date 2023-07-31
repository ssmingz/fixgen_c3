class PlaceHold {
  public void setAuthorFormat(String aFormat) throws ConversionException {
    try {
      mAuthorFormat = aFormat;
      mAuthorFormatRE = Utils.getRE(aFormat);
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
