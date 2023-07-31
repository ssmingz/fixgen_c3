class PlaceHold {
  private void parseArguments(String[] args) {
    Collection locations = new Vector();
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--basedir")) {
        baseDir = new File(args[++i]);
      } else if (args[i].equals("--datafile")) {
        CoverageDataFileHandler.setDefaultDataFile(args[++i]);
      } else if (args[i].equals("--destination")) {
        destinationDirectory = new File(args[++i]);
      } else if (args[i].equals("--ignore")) {
        String regex = args[++i];
        try {
          Perl5Compiler pc = new Perl5Compiler();
          this.ignoreRegexp = pc.compile(regex);
        } catch (MalformedPatternException e) {
          logger.warn(
              (("The regular expression " + regex) + " is invalid: ") + e.getLocalizedMessage());
        }
      } else {
        locations.add(args[i]);
      }
    }
    projectData = ProjectData.getGlobalProjectData();
    Iterator iter = locations.iterator();
    while (iter.hasNext()) {
      addInstrumentation(((String) (iter.next())));
    }
    ProjectData.saveGlobalProjectData();
  }
}
