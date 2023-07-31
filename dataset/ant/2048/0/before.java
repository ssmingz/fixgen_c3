class PlaceHold {
  private boolean isCloneVm() {
    return cloneVm || "true".equals(System.getProperty("build.clonevm"));
  }
}
