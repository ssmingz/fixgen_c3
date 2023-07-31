class PlaceHold {
  protected void executeJava() throws TaskException {
    try {
      log("mode : java");
      Java execTask = null;
      execTask = ((Java) (getProject().createTask("java")));
      execTask.setDir(new File("."));
      execTask.setClassname("com.inprise.server.commandline.EJBUtilities");
      execTask.setClasspath(classpath.concatSystemClasspath());
      execTask.setFork(true);
      execTask.createArg().setValue("generateclient");
      if (debug) {
        execTask.createArg().setValue("-trace");
      }
      execTask.createArg().setValue("-short");
      execTask.createArg().setValue("-jarfile");
      execTask.createArg().setValue(ejbjarfile.getAbsolutePath());
      execTask.createArg().setValue("-single");
      execTask.createArg().setValue("-clientjarfile");
      execTask.createArg().setValue(clientjarfile.getAbsolutePath());
      log("Calling EJBUtilities", MSG_VERBOSE);
      execTask.execute();
    } catch (Exception e) {
      String msg = "Exception while calling generateclient Details: " + e.toString();
      throw new TaskException(msg, e);
    }
  }
}
