class PlaceHold {
  private void buildWebsphereJar(File sourceJar, File destJar) throws TaskException {
    try {
      if (ejbdeploy) {
        String args =
            ((((((" " + sourceJar.getPath()) + " ") + tempdir) + " ") + destJar.getPath()) + " ")
                + getOptions();
        if ((getCombinedClasspath() != null) && (getCombinedClasspath().toString().length() > 0)) {
          args += " -cp " + getCombinedClasspath();
        }
        log("EJB Deploy Options: " + args, MSG_VERBOSE);
        Java javaTask = ((Java) (getTask().getProject().createTask("java")));
        javaTask.createJvmarg().setValue("-Xms64m");
        javaTask.createJvmarg().setValue("-Xmx128m");
        EnvironmentVariable var = new EnvironmentVariable();
        var.setKey("websphere.lib.dir");
        var.setValue(getTask().getProject().getProperty("websphere.home") + "/lib");
        javaTask.addSysproperty(var);
        javaTask.setDir(new File(getTask().getProject().getProperty("websphere.home")));
        javaTask.setClassname("com.ibm.etools.ejbdeploy.EJBDeploy");
        Commandline.Argument arguments = javaTask.createArg();
        arguments.setLine(args);
        Path classpath = wasClasspath;
        if (classpath == null) {
          classpath = getCombinedClasspath();
        }
        if (classpath != null) {
          javaTask.setClasspath(classpath);
          javaTask.setFork(true);
        } else {
          javaTask.setFork(true);
        }
        log("Calling websphere.ejbdeploy for " + sourceJar.toString(), MSG_VERBOSE);
        javaTask.execute();
      }
    } catch (Exception e) {
      String msg = "Exception while calling ejbdeploy. Details: " + e.toString();
      throw new TaskException(msg, e);
    }
  }
}
