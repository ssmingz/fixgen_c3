class PlaceHold {
  public void setCheckFormat(String aFormat) throws ConversionException {
    try {
      mCheckRegexp = Utils.getRE(aFormat);
      mCheckFormat = aFormat;
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
