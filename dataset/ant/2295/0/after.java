class PlaceHold {
  public void execute() throws BuildException {
    File savedBaseDir = baseDir;
    DirectoryScanner scanner;
    String[] list;
    String[] dirs;
    if (xslFile == null) {
      throw new BuildException("no stylesheet specified", getLocation());
    }
    try {
      if (baseDir == null) {
        baseDir = getProject().resolveFile(".");
      }
      liaison = getLiaison();
      if (liaison instanceof XSLTLoggerAware) {
        ((XSLTLoggerAware) (liaison)).setLogger(this);
      }
      log("Using " + liaison.getClass().toString(), MSG_VERBOSE);
      File stylesheet = getProject().resolveFile(xslFile);
      if (!stylesheet.exists()) {
        stylesheet = fileUtils.resolveFile(baseDir, xslFile);
        if (stylesheet.exists()) {
          log("DEPRECATED - the style attribute should be relative " + "to the project\'s");
          log("             basedir, not the tasks\'s basedir.");
        }
      }
      if ((inFile != null) && (outFile != null)) {
        process(inFile, outFile, stylesheet);
        return;
      }
      if (destDir == null) {
        String msg = "destdir attributes must be set!";
        throw new BuildException(msg);
      }
      scanner = getDirectoryScanner(baseDir);
      log("Transforming into " + destDir, MSG_INFO);
      list = scanner.getIncludedFiles();
      for (int i = 0; i < list.length; ++i) {
        process(baseDir, list[i], destDir, stylesheet);
      }
      if (performDirectoryScan) {
        dirs = scanner.getIncludedDirectories();
        for (int j = 0; j < dirs.length; ++j) {
          list = new File(baseDir, dirs[j]).list();
          for (int i = 0; i < list.length; ++i) {
            process(baseDir, list[i], destDir, stylesheet);
          }
        }
      }
    } finally {
      liaison = null;
      stylesheetLoaded = false;
      baseDir = savedBaseDir;
    }
  }
}
