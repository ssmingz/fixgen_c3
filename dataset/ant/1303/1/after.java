class PlaceHold {
  protected void executeForkV5() throws BuildException {
    try {
      log("mode : fork " + BorlandDeploymentTool.BES, MSG_DEBUG);
      ExecTask execTask = new ExecTask(this);
      execTask.setDir(new File("."));
      execTask.setExecutable("iastool");
      if (debug) {
        execTask.createArg().setValue("-debug");
      }
      execTask.createArg().setValue("-genclient");
      execTask.createArg().setValue("-jars");
      execTask.createArg().setValue(ejbjarfile.getAbsolutePath());
      execTask.createArg().setValue("-target");
      execTask.createArg().setValue(clientjarfile.getAbsolutePath());
      execTask.createArg().setValue("-cp");
      execTask.createArg().setValue(classpath.toString());
      log("Calling iastool", MSG_VERBOSE);
      execTask.execute();
    } catch (Exception e) {
      String msg = "Exception while calling generateclient Details: " + e.toString();
      throw new BuildException(msg, e);
    }
  }
}
