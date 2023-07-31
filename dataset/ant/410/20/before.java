class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using jasper compiler");
    Commandline cmd = setupJasperCommand();
    try {
      Java java = null;
      if (getJspc().getClasspath() != null) {
        java.addClasspath(getJspc().getClasspath());
      }
      java.setClassname("org.apache.jasper.JspC");
      String args[] = cmd.getArguments();
      for (int i = 0; i < args.length; i++) {
        java.addArg(new Argument(args[i]));
      }
      java.execute();
      return true;
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error running jsp compiler: ", ex);
      }
    }
  }
}
