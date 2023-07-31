class PlaceHold {
  private void verifyBorlandJar(File sourceJar) throws TaskException {
    Java javaTask = null;
    log("verify " + sourceJar, MSG_INFO);
    try {
      String args = verifyArgs;
      args += " " + sourceJar.getPath();
      javaTask = ((Java) (getTask().getProject().createTask("java")));
      javaTask.setClassname(VERIFY);
      Argument arguments = javaTask.createArg();
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
      throw new TaskException(msg, e);
    }
  }
}
