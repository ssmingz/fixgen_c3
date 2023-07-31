class PlaceHold {
  public void execute() throws ExecutionException {
    setProperty(BASEDIR, getContext().getBaseDir().getAbsolutePath());
    ExecService execService = ((ExecService) (getCoreService(ExecService.class)));
    execService.callTarget(getProperties(), getTargets());
  }
}
