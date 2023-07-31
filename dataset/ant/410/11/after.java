class PlaceHold {
  public boolean execute() throws TaskException {
    getTaskContext().debug("Using SUN rmic compiler");
    Commandline cmd = setupRmicCommand();
    try {
      Class c = Class.forName("sun.rmi.rmic.Main");
      Constructor cons = c.getConstructor(new Class[] {OutputStream.class, String.class});
      Object rmic = cons.newInstance(new Object[] {System.out, "rmic"});
      Method doRmic = c.getMethod("compile", new Class[] {String[].class});
      Boolean ok = ((Boolean) (doRmic.invoke(rmic, new Object[] {cmd.getArguments()})));
      return ok.booleanValue();
    } catch (ClassNotFoundException ex) {
      throw new TaskException(
          ("Cannot use SUN rmic, as it is not available"
                  + " A common solution is to set the environment variable")
              + " JAVA_HOME or CLASSPATH.");
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error starting SUN rmic: ", ex);
      }
    }
  }
}
