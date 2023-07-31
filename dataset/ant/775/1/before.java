class PlaceHold {
  private void populateAttributes() {
    commandline.createArgument().setValue("-o");
    commandline.createArgument().setValue(outputDirectory.toString());
    if (superGrammar != null) {
      commandline.createArgument().setValue("-glib");
      commandline.createArgument().setValue(superGrammar);
    }
    if (html) {
      commandline.createArgument().setValue("-html");
    }
    if (diagnostic) {
      commandline.createArgument().setValue("-diagnostic");
    }
    if (trace) {
      commandline.createArgument().setValue("-trace");
    }
    if (traceParser) {
      commandline.createArgument().setValue("-traceParser");
    }
    if (traceLexer) {
      commandline.createArgument().setValue("-traceLexer");
    }
    if (traceTreeWalker) {
      commandline.createArgument().setValue("-traceTreeWalker");
    }
  }
}
