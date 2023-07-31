class PlaceHold {
  private boolean testIfCondition() {
    if ((ifCondition == null) || "".equals(ifCondition)) {
      return true;
    }
    return project.getProperty(ifCondition) != null;
  }
}
