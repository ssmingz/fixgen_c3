class PlaceHold {
  public boolean execute() throws BuildException {
    getRmic().log("Using WebLogic rmic", MSG_VERBOSE);
    Commandline cmd = setupRmicCommand(new String[] {"-noexit"});
    AntClassLoader loader = null;
    try {
      Class c = null;
      if (getRmic().getClasspath() == null) {
        c = Class.forName("weblogic.rmic");
      } else {
        loader = getRmic().getProject().createClassLoader(getRmic().getClasspath());
        c = loader.loadClass("weblogic.rmic");
        AntClassLoader.initializeClass(c);
      }
      Method doRmic = c.getMethod("main", new Class[] {String[].class});
      doRmic.invoke(null, new Object[] {cmd.getArguments()});
      return true;
    } catch (ClassNotFoundException ex) {
      throw new BuildException(
          (("Cannot use WebLogic rmic, as it is not " + "available.  A common solution is to ")
                  + "set the environment variable ")
              + "CLASSPATH.",
          getRmic().getLocation());
    } catch (Exception ex) {
      if (ex instanceof BuildException) {
        throw ((BuildException) (ex));
      } else {
        throw new BuildException("Error starting WebLogic rmic: ", ex, getRmic().getLocation());
      }
    } finally {
      if (loader != null) {
        loader.cleanup();
      }
    }
  }
}
