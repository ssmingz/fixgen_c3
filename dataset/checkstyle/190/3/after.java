class PlaceHold {
  public void setExcludedClasses(String excludedClasses) throws ConversionException {
    this.excludedClasses = excludedClasses;
    excludedClassesPattern = Utils.createPattern(excludedClasses);
  }
}
