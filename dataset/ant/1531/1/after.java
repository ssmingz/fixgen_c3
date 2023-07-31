class PlaceHold {
  public void execute() throws BuildException {
    validateAttributes();
    if (target.lastModified() > getGeneratedFile().lastModified()) {
      populateAttributes();
      commandline.createArgument().setValue(target.toString());
      log(commandline.describeCommand(), MSG_VERBOSE);
      int err = run(commandline.getCommandline());
      if (err == 1) {
        throw new BuildException("ANTLR returned: " + err, getLocation());
      }
    } else {
      log("Skipped grammar file. Generated file is newer.", MSG_VERBOSE);
    }
  }
}
