class PlaceHold {
  public boolean eval() throws BuildException {
    validate();
    DeweyDecimal actual = getVersion();
    if (null != atLeast) {
      return actual.isGreaterThanOrEqual(new DeweyDecimal(atLeast));
    }
    if (null != exactly) {
      return actual.isEqual(new DeweyDecimal(exactly));
    }
    return false;
  }
}
