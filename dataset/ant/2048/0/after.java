class PlaceHold {
  private boolean isCloneVm() {
    return cloneVm || "true".equals(System.getProperty("ant.build.clonevm"));
  }
}
