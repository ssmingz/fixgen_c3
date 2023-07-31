class PlaceHold {
  private void parseArguments(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-d")) {
        destinationDirectory = new File(args[++i]);
      } else if (args[i].equals("-basedir")) {
        baseDir = new File(args[++i]);
      } else if (args[i].equals("-ignore")) {
        String regex = args[++i];
        try {
          Perl5Compiler pc = new Perl5Compiler();
          this.ignoreRegexp = pc.compile(regex);
        } catch (MalformedPatternException e) {
          logger.warn(
              (("The regular expression " + regex) + " is invalid: ") + e.getLocalizedMessage());
        }
      } else {
        addInstrumentation(args[i]);
      }
    }
  }
}
