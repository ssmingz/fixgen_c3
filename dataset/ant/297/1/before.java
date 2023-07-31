class PlaceHold {
  public void execute() throws AntException {
    setProperty(BASEDIR, getExecService().getBaseDir().getAbsolutePath());
    Object key = getExecService().setupBuild(getProperties(), true);
    setSubBuildKey(key);
    getExecService().runBuild(key, getTargets());
    setSubBuildKey(null);
  }
}
