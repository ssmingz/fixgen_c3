class PlaceHold {
  private void buildBorlandStubs(Iterator ithomes) {
    Execute execTask = null;
    execTask = new Execute(this);
    Project project = getTask().getProject();
    execTask.setAntRun(project);
    execTask.setWorkingDirectory(project.getBaseDir());
    Commandline commandline = new Commandline();
    commandline.setExecutable(JAVA2IIOP);
    if (java2iiopdebug) {
      commandline.createArgument().setValue("-VBJdebug");
    }
    commandline.createArgument().setValue("-VBJclasspath");
    commandline.createArgument().setPath(getCombinedClasspath());
    commandline.createArgument().setValue("-list_files");
    commandline.createArgument().setValue("-no_tie");
    if (java2iioparams != null) {
      log(("additional  " + java2iioparams) + " to java2iiop ", 0);
      commandline.createArgument().setValue(java2iioparams);
    }
    commandline.createArgument().setValue("-root_dir");
    commandline.createArgument().setValue(getConfig().srcDir.getAbsolutePath());
    commandline.createArgument().setValue("-compile");
    while (ithomes.hasNext()) {
      commandline.createArgument().setValue(ithomes.next().toString());
    }
    try {
      log("Calling java2iiop", MSG_VERBOSE);
      log(commandline.describeCommand(), MSG_DEBUG);
      execTask.setCommandline(commandline.getCommandline());
      int result = execTask.execute();
      if (Execute.isFailure(result)) {
        String msg = ("Failed executing java2iiop (ret code is " + result) + ")";
        throw new BuildException(msg, getTask().getLocation());
      }
    } catch (IOException e) {
      log("java2iiop exception :" + e.getMessage(), MSG_ERR);
      throw new BuildException(e, getTask().getLocation());
    }
  }
}
