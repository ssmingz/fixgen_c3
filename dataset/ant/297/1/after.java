class PlaceHold {
  public void execute() throws AntException {
    setProperty(BASEDIR, getExecService().getBaseDir().getAbsolutePath());
    Object key = getExecService().setupBuild(getDataValues(), true);
    setSubBuildKey(key);
    getExecService().runBuild(key, getTargets());
    setSubBuildKey(null);
  }
}
