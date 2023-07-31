class PlaceHold {
  private void parseArguments(String[] args) {
    Collection<Pattern> ignoreRegexes = new Vector<Pattern>();
    coberturaInstrumenter.setIgnoreRegexes(ignoreRegexes);
    File dataFile = CoverageDataFileHandler.getDefaultDataFile();
    List<CoberturaFile> filePaths = new ArrayList<CoberturaFile>();
    String baseDir = null;
    boolean threadsafeRigorous = false;
    boolean ignoreTrivial = false;
    boolean failOnError = false;
    Set<String> ignoreMethodAnnotations = new HashSet<String>();
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--basedir")) {
        baseDir = args[++i];
      } else if (args[i].equals("--datafile")) {
        dataFile = new File(args[++i]);
      } else if (args[i].equals("--destination")) {
        destinationDirectory = new File(args[++i]);
        coberturaInstrumenter.setDestinationDirectory(destinationDirectory);
      } else if (args[i].equals("--ignore")) {
        RegexUtil.addRegex(ignoreRegexes, args[++i]);
      } else if (args[i].equals("--ignoreMethodAnnotation")) {
        ignoreMethodAnnotations.add(args[++i]);
      } else if (args[i].equals("--ignoreTrivial")) {
        ignoreTrivial = true;
      } else if (args[i].equals("--includeClasses")) {
        classPattern.addIncludeClassesRegex(args[++i]);
      } else if (args[i].equals("--excludeClasses")) {
        classPattern.addExcludeClassesRegex(args[++i]);
      } else if (args[i].equals("--failOnError")) {
        failOnError = true;
        logger.setFailOnError(true);
      } else if (args[i].equals("--threadsafeRigorous")) {
        threadsafeRigorous = true;
      } else if (args[i].equals("--auxClasspath")) {
        addElementsToJVM(args[++i]);
      } else {
        filePaths.add(new CoberturaFile(baseDir, args[i]));
      }
    }
    coberturaInstrumenter.setIgnoreTrivial(ignoreTrivial);
    coberturaInstrumenter.setIgnoreMethodAnnotations(ignoreMethodAnnotations);
    coberturaInstrumenter.setThreadsafeRigorous(threadsafeRigorous);
    coberturaInstrumenter.setFailOnError(failOnError);
    ProjectData projectData;
    projectData =
        (dataFile.isFile())
            ? CoverageDataFileHandler.loadCoverageData(dataFile)
            : new ProjectData();
    coberturaInstrumenter.setProjectData(projectData);
    logger.info(
        ((("Instrumenting " + filePaths.size()) + " ") + (filePaths.size() == 1 ? "file" : "files"))
            + (destinationDirectory != null
                ? " to " + destinationDirectory.getAbsoluteFile()
                : ""));
    Iterator<CoberturaFile> iter = filePaths.iterator();
    while (iter.hasNext()) {
      CoberturaFile coberturaFile = iter.next();
      if (coberturaFile.isArchive()) {
        addInstrumentationToArchive(coberturaFile);
      } else {
        addInstrumentation(coberturaFile);
      }
    }
    CoverageDataFileHandler.saveCoverageData(projectData, dataFile);
  }
}
