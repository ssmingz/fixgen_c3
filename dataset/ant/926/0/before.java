class PlaceHold {
  public void test5() {
    expectBuildException("test5", "No public execute() in " + Project.class);
  }
}
