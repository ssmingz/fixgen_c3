class PlaceHold {
  public void execute() throws BuildException {
    if (((baseDir == null) && (filesets.size() == 0)) && "zip".equals(archiveType)) {
      throw new BuildException(
          "basedir attribute must be set, or at least " + "one fileset must be given!");
    }
    if (zipFile == null) {
      throw new BuildException(("You must specify the " + archiveType) + " file to create!");
    }
    File renamedFile = null;
    addingNewFiles = true;
    doUpdate = doUpdate && zipFile.exists();
    if (doUpdate) {
      FileUtils fileUtils = FileUtils.newFileUtils();
      renamedFile = fileUtils.createTempFile("zip", ".tmp", fileUtils.getParentFile(zipFile));
      try {
        if (!zipFile.renameTo(renamedFile)) {
          throw new BuildException("Unable to rename old file to temporary file");
        }
      } catch (SecurityException e) {
        throw new BuildException("Not allowed to rename old file to temporary file");
      }
    }
    Vector dss = new Vector();
    if (baseDir != null) {
      dss.addElement(getDirectoryScanner(baseDir));
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      dss.addElement(fs.getDirectoryScanner(project));
    }
    int dssSize = dss.size();
    FileScanner[] scanners = new FileScanner[dssSize];
    dss.copyInto(scanners);
    if (isUpToDate(scanners, zipFile)) {
      return;
    }
    String action = (doUpdate) ? "Updating " : "Building ";
    log(((action + archiveType) + ": ") + zipFile.getAbsolutePath());
    boolean success = false;
    try {
      ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(zipFile));
      zOut.setEncoding(encoding);
      try {
        if (doCompress) {
          zOut.setMethod(DEFLATED);
        } else {
          zOut.setMethod(STORED);
        }
        initZipOutputStream(zOut);
        if (baseDir != null) {
          addFiles(getDirectoryScanner(baseDir), zOut, "", "");
        }
        addFiles(filesets, zOut);
        if (doUpdate) {
          addingNewFiles = false;
          ZipFileSet oldFiles = new ZipFileSet();
          oldFiles.setSrc(renamedFile);
          StringBuffer exclusionPattern = new StringBuffer();
          for (int i = 0; i < addedFiles.size(); i++) {
            if (i != 0) {
              exclusionPattern.append(",");
            }
            exclusionPattern.append(((String) (addedFiles.elementAt(i))));
          }
          oldFiles.setExcludes(exclusionPattern.toString());
          Vector tmp = new Vector();
          tmp.addElement(oldFiles);
          addFiles(tmp, zOut);
        }
        finalizeZipOutputStream(zOut);
        success = true;
      } finally {
        try {
          if (zOut != null) {
            zOut.close();
          }
        } catch (IOException ex) {
          if (success) {
            throw ex;
          }
        }
      }
    } catch (IOException ioe) {
      String msg = (("Problem creating " + archiveType) + ": ") + ioe.getMessage();
      if (!zipFile.delete()) {
        msg += " (and the archive is probably corrupt but I could not delete it)";
      }
      if (doUpdate) {
        if (!renamedFile.renameTo(zipFile)) {
          msg += (" (and I couldn't rename the temporary file " + renamedFile.getName()) + " back)";
        }
      }
      throw new BuildException(msg, ioe, location);
    } finally {
      cleanUp();
    }
    if (success && doUpdate) {
      if (!renamedFile.delete()) {
        log("Warning: unable to delete temporary file " + renamedFile.getName(), MSG_WARN);
      }
    }
  }
}
