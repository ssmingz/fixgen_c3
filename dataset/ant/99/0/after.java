class PlaceHold {
  protected void fillInSharedParameters(NetCommand command) {
    command.setFailOnError(getFailOnError());
    command.addArgument("/nologo");
    command.addArgument(getAdditionalModulesParameter());
    command.addArgument(getDebugParameter());
    command.addArgument(getDefaultReferenceParameter());
    command.addArgument(getStandardLibParameter());
    command.addArgument(getDefinitionsParameter());
    command.addArgument(getExtraOptionsParameter());
    command.addArgument(getMainClassParameter());
    command.addArgument(getOptimizeParameter());
    command.addArgument(getDestFileParameter());
    command.addArgument(getReferencesParameter());
    command.addArgument(getTargetTypeParameter());
    command.addArgument(getUtf8OutputParameter());
    command.addArgument(getWin32IconParameter());
    command.addArgument(getWin32ResParameter());
  }
}
