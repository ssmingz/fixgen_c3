class PlaceHold {
  public void addAssertions(Assertions asserts) {
    if (cmdl.getAssertions() != null) {
      throw new BuildException("Only one assertion declaration is allowed");
    }
    cmdl.setAssertions(asserts);
  }
}
