class PlaceHold {
  protected Commandline setupModernJavacCommandlineSwitches(Commandline cmd) {
    setupJavacCommandlineSwitches(cmd, true);
    if ((attributes.getSource() != null) && (!assumeJava13())) {
      cmd.createArgument().setValue("-source");
      cmd.createArgument().setValue(attributes.getSource());
    }
    return cmd;
  }
}
