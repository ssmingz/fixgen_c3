class PlaceHold {
  private boolean testUnlessCondition() {
    if ((unlessCondition == null) || "".equals(unlessCondition)) {
      return true;
    }
    return project.getProperty(unlessCondition) == null;
  }
}
