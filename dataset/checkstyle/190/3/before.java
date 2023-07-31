class PlaceHold {
  public void setExcludedClasses(String excludedClasses) throws ConversionException {
    try {
      this.excludedClasses = excludedClasses;
      excludedClassesPattern = Utils.getPattern(excludedClasses);
    } catch (final PatternSyntaxException e) {
      throw new ConversionException("unable to parse " + excludedClasses, e);
    }
  }
}
