class PlaceHold {
  protected void executeFork() throws TaskException {
    try {
      getLogger().info("mode : fork");
      ExecTask execTask = null;
      execTask = ((ExecTask) (getProject().createTask("exec")));
      execTask.setDir(new File("."));
      execTask.setExecutable("iastool");
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
      getLogger().debug("Calling java2iiop");
      execTask.execute();
    } catch (Exception e) {
      String msg = "Exception while calling generateclient Details: " + e.toString();
      throw new TaskException(msg, e);
    }
  }
}
