class PlaceHold {
  private void initializeProject() {
    Vector listeners = project.getBuildListeners();
    for (int i = 0; i < listeners.size(); i++) {
      p1.addBuildListener(((BuildListener) (listeners.elementAt(i))));
    }
    if (output != null) {
      try {
        PrintStream out = new PrintStream(new FileOutputStream(output));
        DefaultLogger logger = new DefaultLogger();
        logger.setMessageOutputLevel(MSG_INFO);
        logger.setOutputPrintStream(out);
        logger.setErrorPrintStream(out);
        p1.addBuildListener(logger);
      } catch (IOException ex) {
        log("Ant: Can't set output to " + output);
      }
    }
    Hashtable taskdefs = project.getTaskDefinitions();
    Enumeration et = taskdefs.keys();
    while (et.hasMoreElements()) {
      String taskName = ((String) (et.nextElement()));
      Class taskClass = ((Class) (taskdefs.get(taskName)));
      p1.addTaskDefinition(taskName, taskClass);
    }
    Hashtable prop1 = project.getProperties();
    Enumeration e = prop1.keys();
    while (e.hasMoreElements()) {
      String arg = ((String) (e.nextElement()));
      String value = ((String) (prop1.get(arg)));
      p1.setProperty(arg, value);
    }
  }
}
