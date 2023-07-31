class PlaceHold {
  public void setMatch(String match) {
    if (regex != null) {
      throw new BuildException("Only one regular expression is allowed");
    }
    regex = new RegularExpression();
    regex.setPattern(match);
  }
}
