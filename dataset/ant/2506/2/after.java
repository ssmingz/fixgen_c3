class PlaceHold {
  public void execute() throws BuildException {
    if (((baseDir == null) && (filesets.size() == 0)) && "zip".equals(archiveType)) {
      throw new BuildException(
          "basedir attribute must be set, or at least one fileset must be given!");
    }
    if (zipFile == null) {
      throw new BuildException(("You must specify the " + archiveType) + "file to create!");
    }
    Vector dss = new Vector();
    if (baseDir != null) {
      dss.addElement(getDirectoryScanner(baseDir));
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      dss.addElement(fs.getDirectoryScanner(project));
    }
    FileScanner[] scanners = new FileScanner[dss.size()];
    dss.copyInto(scanners);
    if (isUpToDate(scanners, zipFile)) {
      return;
    }
    log((("Building " + archiveType) + ": ") + zipFile.getAbsolutePath());
    try {
      ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(zipFile));
      try {
        if (doCompress) {
          zOut.setMethod(ZipOutputStream.DEFLATED);
        } else {
          zOut.setMethod(ZipOutputStream.STORED);
        }
        initZipOutputStream(zOut);
        for (int j = 0; j < scanners.length; j++) {
          addFiles(scanners[j], zOut, "");
        }
      } finally {
        zOut.close();
      }
    } catch (IOException ioe) {
      String msg = (("Problem creating " + archiveType) + ": ") + ioe.getMessage();
      if (!zipFile.delete()) {
        msg += " (and the archive is probably corrupt but I could not delete it)";
      }
      throw new BuildException(msg, ioe, location);
    }
  }
}
