class PlaceHold {
  public void execute() {
    if (!initialized) {
      init();
    }
    if (subTarget == null) {
      throw new BuildException("Attribute target is required.", location);
    }
    callee.setDir(project.getBaseDir());
    callee.setAntfile(project.getProperty("ant.file"));
    callee.setTarget(subTarget);
    callee.setInheritAll(inheritAll);
    callee.execute();
  }
}
