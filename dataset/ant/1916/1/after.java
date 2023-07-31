class PlaceHold {
  public boolean execute() throws TaskException {
    getTaskContext().debug("Using WebLogic rmic");
    final ExecuteJava exe = new ExecuteJava();
    exe.setClassName("weblogic.rmic");
    final ArgumentList cmd = setupRmicCommand(new String[] {"-noexit"});
    exe.getArguments().addArguments(cmd);
    exe.execute(getTaskContext());
    return true;
  }
}
