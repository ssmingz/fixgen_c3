class PlaceHold {
  public void executeOneFile(String targetFile) throws BuildException {
    NetCommand command = new NetCommand(this, exe_title, exe_name);
    command.setFailOnError(getFailFailOnError());
    command.setTraceCommandLine(true);
    command.addArgument(getDebugParameter());
    command.addArgument(getTargetTypeParameter());
    command.addArgument(getListingParameter());
    command.addArgument(getOutputFileParameter());
    command.addArgument(getOwnerParameter());
    command.addArgument(getResourceFileParameter());
    command.addArgument(getVerboseParameter());
    command.addArgument(getExtraOptionsParameter());
    command.addArgument(targetFile);
    command.runCommand();
  }
}
