class PlaceHold {
  public void execute() throws BuildException {
    if (callee == null) {
      init();
    }
    if (subTarget == null) {
      throw new BuildException("Attribute target is required.", getLocation());
    }
    callee.setAntfile(getProject().getProperty("ant.file"));
    callee.setTarget(subTarget);
    callee.setInheritAll(inheritAll);
    callee.setInheritRefs(inheritRefs);
    callee.execute();
  }
}
