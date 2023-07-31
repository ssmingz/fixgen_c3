class PlaceHold {
  private void buildWebsphereJar(File sourceJar, File destJar) {
    try {
      if (ejbdeploy) {
        Java javaTask = new Java(getTask());
        javaTask.createJvmarg().setValue("-Xms64m");
        javaTask.createJvmarg().setValue("-Xmx128m");
        Environment.Variable var = new Environment.Variable();
        var.setKey("websphere.lib.dir");
        File libdir = new File(websphereHome, "lib");
        var.setValue(libdir.getAbsolutePath());
        javaTask.addSysproperty(var);
        javaTask.setDir(websphereHome);
        javaTask.setTaskName("ejbdeploy");
        javaTask.setClassname("com.ibm.etools.ejbdeploy.EJBDeploy");
        javaTask.createArg().setValue(sourceJar.getPath());
        javaTask.createArg().setValue(tempdir);
        javaTask.createArg().setValue(destJar.getPath());
        javaTask.createArg().setLine(getOptions());
        if ((getCombinedClasspath() != null) && (getCombinedClasspath().toString().length() > 0)) {
          javaTask.createArg().setValue("-cp");
          javaTask.createArg().setValue(getCombinedClasspath().toString());
        }
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
      throw new BuildException(msg, e);
    }
  }
}
