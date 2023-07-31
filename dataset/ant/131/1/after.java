class PlaceHold {
  protected Commandline setupModernJavacCommandlineSwitches(Commandline cmd) {
    setupJavacCommandlineSwitches(cmd, true);
    if ((attributes.getSource() != null) && (!assumeJava13())) {
      cmd.createArgument().setValue("-source");
      String source = attributes.getSource();
      if (assumeJava14() && (source.equals("1.1") || source.equals("1.2"))) {
        cmd.createArgument().setValue("1.3");
      } else {
        cmd.createArgument().setValue(source);
      }
    }
    return cmd;
  }
}
