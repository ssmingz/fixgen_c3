class PlaceHold {
  public void setExcludedClasses(String aExcludedClasses) throws ConversionException {
    try {
      mExcludedClasses = aExcludedClasses;
      mExcludedClassesPattern = Utils.getPattern(mExcludedClasses);
    } catch (PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + mExcludedClasses, e);
    }
  }
}
