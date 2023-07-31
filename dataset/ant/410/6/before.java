class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using Kaffe rmic");
    Commandline cmd = setupRmicCommand();
    try {
      Class c = Class.forName("kaffe.rmi.rmic.RMIC");
      Constructor cons = c.getConstructor(new Class[] {String[].class});
      Object rmic = cons.newInstance(new Object[] {cmd.getArguments()});
      Method doRmic = c.getMethod("run", null);
      String str[] = cmd.getArguments();
      Boolean ok = ((Boolean) (doRmic.invoke(rmic, null)));
      return ok.booleanValue();
    } catch (ClassNotFoundException ex) {
      throw new TaskException(
          ("Cannot use Kaffe rmic, as it is not available"
                  + " A common solution is to set the environment variable")
              + " JAVA_HOME or CLASSPATH.");
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error starting Kaffe rmic: ", ex);
      }
    }
  }
}
