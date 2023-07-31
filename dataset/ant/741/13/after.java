class PlaceHold {
  private boolean testIfCondition() {
    if ((ifCondition == null) || "".equals(ifCondition)) {
      return true;
    }
    return getProject().getProperty(ifCondition) != null;
  }
}
