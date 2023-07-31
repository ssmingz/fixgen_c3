class PlaceHold {
  private void initializeProject() {
    newProject.setInputHandler(getProject().getInputHandler());
    Iterator iter = getBuildListeners();
    while (iter.hasNext()) {
      newProject.addBuildListener(((BuildListener) (iter.next())));
    }
    if (output != null) {
      File outfile = null;
      if (dir != null) {
        outfile = FILE_UTILS.resolveFile(dir, output);
      } else {
        outfile = getProject().resolveFile(output);
      }
      try {
        out = new PrintStream(new FileOutputStream(outfile));
        DefaultLogger logger = new DefaultLogger();
        logger.setMessageOutputLevel(MSG_INFO);
        logger.setOutputPrintStream(out);
        logger.setErrorPrintStream(out);
        newProject.addBuildListener(logger);
      } catch (IOException ex) {
        log("Ant: Can't set output to " + output);
      }
    }
    getProject().initSubProject(newProject);
    getProject().copyUserProperties(newProject);
    if (!inheritAll) {
      newProject.setSystemProperties();
    } else {
      addAlmostAll(getProject().getProperties());
    }
    Enumeration e = propertySets.elements();
    while (e.hasMoreElements()) {
      PropertySet ps = ((PropertySet) (e.nextElement()));
      addAlmostAll(ps.getProperties());
    }
  }
}
