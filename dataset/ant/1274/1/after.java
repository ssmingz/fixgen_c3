class PlaceHold {
  private void buildWeblogicJar(File sourceJar, File destJar, String publicId) {
    Java javaTask = null;
    if (noEJBC) {
      try {
        FILE_UTILS.copyFile(sourceJar, destJar);
        if (!keepgenerated) {
          sourceJar.delete();
        }
        return;
      } catch (IOException e) {
        throw new BuildException("Unable to write EJB jar", e);
      }
    }
    String ejbcClassName = ejbcClass;
    try {
      javaTask = new Java(getTask());
      javaTask.setTaskName("ejbc");
      javaTask.createJvmarg().setLine(additionalJvmArgs);
      if (!sysprops.isEmpty()) {
        for (Enumeration en = sysprops.elements(); en.hasMoreElements(); ) {
          Environment.Variable entry = ((Environment.Variable) (en.nextElement()));
          javaTask.addSysproperty(entry);
        }
      }
      if (getJvmDebugLevel() != null) {
        javaTask.createJvmarg().setLine(" -Dweblogic.StdoutSeverityLevel=" + jvmDebugLevel);
      }
      if (ejbcClassName == null) {
        if (PUBLICID_EJB11.equals(publicId)) {
          ejbcClassName = COMPILER_EJB11;
        } else if (PUBLICID_EJB20.equals(publicId)) {
          ejbcClassName = COMPILER_EJB20;
        } else {
          log(("Unrecognized publicId " + publicId) + " - using EJB 1.1 compiler", MSG_WARN);
          ejbcClassName = COMPILER_EJB11;
        }
      }
      javaTask.setClassname(ejbcClassName);
      javaTask.createArg().setLine(additionalArgs);
      if (keepgenerated) {
        javaTask.createArg().setValue("-keepgenerated");
      }
      if (compiler == null) {
        String buildCompiler = getTask().getProject().getProperty("build.compiler");
        if ((buildCompiler != null) && buildCompiler.equals("jikes")) {
          javaTask.createArg().setValue("-compiler");
          javaTask.createArg().setValue("jikes");
        }
      } else if (!compiler.equals(DEFAULT_COMPILER)) {
        javaTask.createArg().setValue("-compiler");
        javaTask.createArg().setLine(compiler);
      }
      Path combinedClasspath = getCombinedClasspath();
      if (((wlClasspath != null) && (combinedClasspath != null))
          && (combinedClasspath.toString().trim().length() > 0)) {
        javaTask.createArg().setValue("-classpath");
        javaTask.createArg().setPath(combinedClasspath);
      }
      javaTask.createArg().setValue(sourceJar.getPath());
      if (outputDir == null) {
        javaTask.createArg().setValue(destJar.getPath());
      } else {
        javaTask.createArg().setValue(outputDir.getPath());
      }
      Path classpath = wlClasspath;
      if (classpath == null) {
        classpath = getCombinedClasspath();
      }
      javaTask.setFork(true);
      if (classpath != null) {
        javaTask.setClasspath(classpath);
      }
      log((("Calling " + ejbcClassName) + " for ") + sourceJar.toString(), MSG_VERBOSE);
      if (javaTask.executeJava() != 0) {
        throw new BuildException("Ejbc reported an error");
      }
    } catch (Exception e) {
      String msg = (("Exception while calling " + ejbcClassName) + ". Details: ") + e.toString();
      throw new BuildException(msg, e);
    }
  }
}
