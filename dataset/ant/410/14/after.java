class PlaceHold {
  public boolean execute() throws TaskException {
    getTaskContext().debug("Using WebLogic rmic");
    Commandline cmd = setupRmicCommand(new String[] {"-noexit"});
    try {
      Class c = Class.forName("weblogic.rmic");
      Method doRmic = c.getMethod("main", new Class[] {String[].class});
      doRmic.invoke(null, new Object[] {cmd.getArguments()});
      return true;
    } catch (ClassNotFoundException ex) {
      throw new TaskException(
          ("Cannot use WebLogic rmic, as it is not available"
                  + " A common solution is to set the environment variable")
              + " CLASSPATH.");
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error starting WebLogic rmic: ", ex);
      }
    }
  }
}
