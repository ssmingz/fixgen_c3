class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using kjc compiler");
    Commandline cmd = setupKjcCommand();
    try {
      Class c = Class.forName("at.dms.kjc.Main");
      Method compile = c.getMethod("compile", new Class[] {String[].class});
      Boolean ok = ((Boolean) (compile.invoke(null, new Object[] {cmd.getArguments()})));
      return ok.booleanValue();
    } catch (ClassNotFoundException ex) {
      throw new TaskException(
          ("Cannot use kjc compiler, as it is not available"
                  + " A common solution is to set the environment variable")
              + " CLASSPATH to your kjc archive (kjc.jar).");
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error starting kjc compiler: ", ex);
      }
    }
  }
}
