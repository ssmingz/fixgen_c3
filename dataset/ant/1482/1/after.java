class PlaceHold {
  public void execute() throws BuildException {
    if ("style".equals(getTaskType())) {
      log("Warning: the task name <style> is deprecated. Use <xslt> instead.", MSG_WARN);
    }
    File savedBaseDir = baseDir;
    DirectoryScanner scanner;
    String[] list;
    String[] dirs;
    if ((xslResource == null) && (xslFile == null)) {
      throw new BuildException(
          ("specify the " + "stylesheet either as a filename in style ")
              + "attribute or as a nested resource",
          getLocation());
    }
    if ((xslResource != null) && (xslFile != null)) {
      throw new BuildException(
          (("specify the " + "stylesheet either as a filename in style ")
                  + "attribute or as a nested resource but not ")
              + "as both",
          getLocation());
    }
    if ((inFile != null) && (!inFile.exists())) {
      throw new BuildException(
          ("input file " + inFile.toString()) + " does not exist", getLocation());
    }
    try {
      if (baseDir == null) {
        baseDir = FILE_UTILS.resolveFile(getProject().getBaseDir(), ".");
      }
      liaison = getLiaison();
      if (liaison instanceof XSLTLoggerAware) {
        ((XSLTLoggerAware) (liaison)).setLogger(this);
      }
      log("Using " + liaison.getClass().toString(), MSG_VERBOSE);
      if (xslFile != null) {
        File stylesheet = FILE_UTILS.resolveFile(getProject().getBaseDir(), xslFile);
        if (!stylesheet.exists()) {
          stylesheet = FILE_UTILS.resolveFile(baseDir, xslFile);
          if (stylesheet.exists()) {
            log("DEPRECATED - the 'style' attribute should be relative " + "to the project's");
            log("             basedir, not the tasks's basedir.");
          }
        }
        FileResource fr = new FileResource();
        fr.setProject(getProject());
        fr.setFile(stylesheet);
        xslResource = fr;
      }
      if ((inFile != null) && (outFile != null)) {
        process(inFile, outFile, xslResource);
        return;
      }
      checkDest();
      if (useImplicitFileset) {
        scanner = getDirectoryScanner(baseDir);
        log("Transforming into " + destDir, MSG_INFO);
        list = scanner.getIncludedFiles();
        for (int i = 0; i < list.length; ++i) {
          process(baseDir, list[i], destDir, xslResource);
        }
        if (performDirectoryScan) {
          dirs = scanner.getIncludedDirectories();
          for (int j = 0; j < dirs.length; ++j) {
            list = new File(baseDir, dirs[j]).list();
            for (int i = 0; i < list.length; ++i) {
              process(baseDir, (dirs[j] + File.separator) + list[i], destDir, xslResource);
            }
          }
        }
      } else if (resources.size() == 0) {
        throw new BuildException("no resources specified");
      }
      processResources(xslResource);
    } finally {
      if (loader != null) {
        loader.resetThreadContextLoader();
        loader.cleanup();
        loader = null;
      }
      liaison = null;
      stylesheetLoaded = false;
      baseDir = savedBaseDir;
    }
  }
}
