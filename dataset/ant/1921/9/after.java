class PlaceHold {
  public void execute() throws BuildException {
    validateAttributes();
    File generatedFile = getGeneratedFile();
    boolean targetIsOutOfDate = target.lastModified() > generatedFile.lastModified();
    boolean superGrammarIsOutOfDate =
        (superGrammar != null) && (superGrammar.lastModified() > generatedFile.lastModified());
    if (targetIsOutOfDate || superGrammarIsOutOfDate) {
      if (targetIsOutOfDate) {
        log((("Compiling " + target) + " as it is newer than ") + generatedFile, MSG_VERBOSE);
      } else if (superGrammarIsOutOfDate) {
        log(
            (((("Compiling " + target) + " as ") + superGrammar) + " is newer than ")
                + generatedFile,
            MSG_VERBOSE);
      }
      populateAttributes();
      commandline.createArgument().setValue(target.toString());
      log(commandline.describeCommand(), MSG_VERBOSE);
      int err = run(commandline.getCommandline());
      if (Execute.isFailure(err)) {
        throw new BuildException("ANTLR returned: " + err, getLocation());
      } else {
        String output = bos.toString();
        if (output.indexOf("error:") > (-1)) {
          throw new BuildException("ANTLR signaled an error: " + output, getLocation());
        }
      }
    } else {
      log(("Skipped grammar file. Generated file " + generatedFile) + "is newer.", MSG_VERBOSE);
    }
  }
}
