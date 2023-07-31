class PlaceHold {
  private void verifyBorlandJarV4(File sourceJar) {
    Java javaTask = null;
    log("verify BAS " + sourceJar, MSG_INFO);
    try {
      String args = verifyArgs;
      args += " " + sourceJar.getPath();
      javaTask = ((Java) (getTask().getProject().createTask("java")));
      javaTask.setTaskName("verify");
      javaTask.setClassname(VERIFY);
      Commandline.Argument arguments = javaTask.createArg();
      arguments.setLine(args);
      Path classpath = getCombinedClasspath();
      if (classpath != null) {
        javaTask.setClasspath(classpath);
        javaTask.setFork(true);
      }
      log((("Calling " + VERIFY) + " for ") + sourceJar.toString(), MSG_VERBOSE);
      javaTask.execute();
    } catch (Exception e) {
      String msg = (("Exception while calling " + VERIFY) + " Details: ") + e.toString();
      throw new BuildException(msg, e);
    }
  }
}
