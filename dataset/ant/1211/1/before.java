class PlaceHold {
  public void execute() throws ExecutionException {
    if (baseDir == null) {
      baseDir = getContext().getBaseDir();
    }
    if (antFile == null) {
      antFile = new File(baseDir, "build.ant");
      if (!antFile.exists()) {
        antFile = new File(baseDir, "build.xml");
      }
    }
    ExecService execService = ((ExecService) (getCoreService(ExecService.class)));
    execService.runBuild(antFile, getProperties(), getTargets());
  }
}
