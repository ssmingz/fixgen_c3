class PlaceHold {
  public void execute() throws ExecutionException {
    ExecService execService = ((ExecService) (getCoreService(ExecService.class)));
    execService.callTarget(getProperties(), getTargets());
  }
}
