class PlaceHold {
  private void addGenICGeneratedFiles(File genericJarFile, Hashtable ejbFiles) {
    Java genicTask = null;
    String genicClass = null;
    if (nogenic) {
      return;
    }
    genicTask = ((Java) (getTask().getProject().createTask("java")));
    genicTask.setTaskName("genic");
    genicTask.setFork(true);
    genicTask.createJvmarg().setValue("-Dinstall.root=" + jonasroot);
    String jonasConfigDir = (jonasroot + File.separator) + "config";
    File javaPolicyFile = new File(jonasConfigDir, "java.policy");
    if (javaPolicyFile.exists()) {
      genicTask.createJvmarg().setValue("-Djava.security.policy=" + javaPolicyFile.toString());
    }
    try {
      outputdir = createTempDir();
    } catch (IOException aIOException) {
      String msg = "Cannot create temp dir: " + aIOException.getMessage();
      throw new BuildException(msg, aIOException);
    }
    log("Using temporary output directory: " + outputdir, MSG_VERBOSE);
    genicTask.createArg().setValue("-d");
    genicTask.createArg().setFile(outputdir);
    String key;
    File f;
    Enumeration keys = ejbFiles.keys();
    while (keys.hasMoreElements()) {
      key = ((String) (keys.nextElement()));
      f = new File((outputdir + File.separator) + key);
      f.getParentFile().mkdirs();
    }
    log("Worked around a bug of GenIC 2.5.", MSG_VERBOSE);
    Path classpath = getCombinedClasspath();
    if (classpath == null) {
      classpath = new Path(getTask().getProject());
    }
    classpath.append(new Path(classpath.getProject(), jonasConfigDir));
    classpath.append(new Path(classpath.getProject(), outputdir.toString()));
    if (orb != null) {
      String orbJar =
          ((((jonasroot + File.separator) + "lib") + File.separator) + orb) + "_jonas.jar";
      classpath.append(new Path(classpath.getProject(), orbJar));
    }
    log("Using classpath: " + classpath.toString(), MSG_VERBOSE);
    genicTask.setClasspath(classpath);
    genicClass = getGenicClassName(classpath);
    if (genicClass == null) {
      log("Cannot find GenIC class in classpath.", MSG_ERR);
      throw new BuildException("GenIC class not found, please check the classpath.");
    } else {
      log(("Using '" + genicClass) + "' GenIC class.", MSG_VERBOSE);
      genicTask.setClassname(genicClass);
    }
    if (keepgenerated) {
      genicTask.createArg().setValue("-keepgenerated");
    }
    if (nocompil) {
      genicTask.createArg().setValue("-nocompil");
    }
    if (novalidation) {
      genicTask.createArg().setValue("-novalidation");
    }
    if (javac != null) {
      genicTask.createArg().setValue("-javac");
      genicTask.createArg().setLine(javac);
    }
    if ((javacopts != null) && (!javacopts.equals(""))) {
      genicTask.createArg().setValue("-javacopts");
      genicTask.createArg().setLine(javacopts);
    }
    if ((rmicopts != null) && (!rmicopts.equals(""))) {
      genicTask.createArg().setValue("-rmicopts");
      genicTask.createArg().setLine(rmicopts);
    }
    if (secpropag) {
      genicTask.createArg().setValue("-secpropag");
    }
    if (verbose) {
      genicTask.createArg().setValue("-verbose");
    }
    if (additionalargs != null) {
      genicTask.createArg().setValue(additionalargs);
    }
    genicTask.createArg().setValue("-noaddinjar");
    genicTask.createArg().setValue(genericJarFile.getPath());
    log(
        ((((("Calling " + genicClass) + " for ") + getConfig().descriptorDir) + File.separator)
                + descriptorName)
            + ".",
        MSG_VERBOSE);
    if (genicTask.executeJava() != 0) {
      log(("Deleting temp output directory '" + outputdir) + "'.", MSG_VERBOSE);
      deleteAllFiles(outputdir);
      if (!keepgeneric) {
        log("Deleting generic JAR " + genericJarFile.toString(), MSG_VERBOSE);
        genericJarFile.delete();
      }
      throw new BuildException("GenIC reported an error.");
    }
    addAllFiles(outputdir, "", ejbFiles);
  }
}
