class PlaceHold {
  public void execute() throws BuildException {
    validateAttributes();
    if (target.lastModified() > getGeneratedFile().lastModified()) {
      commandline.createArgument().setValue("-o");
      commandline.createArgument().setValue(outputDirectory.toString());
      commandline.createArgument().setValue(target.toString());
      log("Forking " + commandline.toString(), MSG_VERBOSE);
      int err = run(commandline.getCommandline());
      if (err == 1) {
        throw new BuildException("ANTLR returned: " + err, location);
      }
    } else {
      log("Skipped grammar file. Generated file is newer.", MSG_VERBOSE);
    }
  }
}
