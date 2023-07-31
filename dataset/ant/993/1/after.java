class PlaceHold {
  public void addAssertions(Assertions asserts) {
    if (commandline.getAssertions() != null) {
      throw new BuildException("Only one assertion declaration is allowed");
    }
    commandline.setAssertions(asserts);
  }
}
