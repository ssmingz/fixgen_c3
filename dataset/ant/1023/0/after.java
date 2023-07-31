class PlaceHold {
  public boolean execute() throws TaskException {
    getTaskContext().debug("Using classic compiler");
    ArgumentList cmd = setupJavacCommand(true);
    try {
      Class c = Class.forName("sun.tools.javac.Main");
      Constructor cons = c.getConstructor(new Class[] {OutputStream.class, String.class});
      Object compiler = cons.newInstance(new Object[] {System.out, "javac"});
      Method compile = c.getMethod("compile", new Class[] {String[].class});
      Boolean ok = ((Boolean) (compile.invoke(compiler, new Object[] {cmd.getArguments()})));
      return ok.booleanValue();
    } catch (ClassNotFoundException ex) {
      throw new TaskException(
          ("Cannot use classic compiler, as it is not available"
                  + " A common solution is to set the environment variable")
              + " JAVA_HOME to your jdk directory.");
    } catch (Exception ex) {
      if (ex instanceof TaskException) {
        throw ((TaskException) (ex));
      } else {
        throw new TaskException("Error starting classic compiler: ", ex);
      }
    }
  }
}
