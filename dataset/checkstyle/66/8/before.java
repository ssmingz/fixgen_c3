class PlaceHold {
  public void setIgnoreName(String aFormat) throws ConversionException {
    try {
      mIgnoreNameRegexp = Utils.getRE(aFormat);
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
