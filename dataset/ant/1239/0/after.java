class PlaceHold {
  public void setMatch(String match) throws TaskException {
    if (regex != null) {
      throw new TaskException("Only one regular expression is allowed");
    }
    regex = new RegularExpression();
    regex.setPattern(match);
  }
}
