class PlaceHold {
  public void execute() throws BuildException {
    if (tarFile == null) {
      throw new BuildException("tarfile attribute must be set!", getLocation());
    }
    if (tarFile.exists() && tarFile.isDirectory()) {
      throw new BuildException("tarfile is a directory!", getLocation());
    }
    if (tarFile.exists() && (!tarFile.canWrite())) {
      throw new BuildException("Can not write to the specified tarfile!", getLocation());
    }
    Vector savedFileSets = ((Vector) (filesets.clone()));
    try {
      if (baseDir != null) {
        if (!baseDir.exists()) {
          throw new BuildException("basedir does not exist!", getLocation());
        }
        TarFileSet mainFileSet = new TarFileSet(fileset);
        mainFileSet.setDir(baseDir);
        filesets.addElement(mainFileSet);
      }
      if ((filesets.size() == 0) && (resourceCollections.size() == 0)) {
        throw new BuildException(
            ("You must supply either a basedir " + "attribute or some nested resource")
                + " collections.",
            getLocation());
      }
      boolean upToDate = true;
      for (Enumeration e = filesets.elements(); e.hasMoreElements(); ) {
        upToDate &= check(((TarFileSet) (e.nextElement())));
      }
      for (Enumeration e = resourceCollections.elements(); e.hasMoreElements(); ) {
        upToDate &= check(((ResourceCollection) (e.nextElement())));
      }
      if (upToDate) {
        log(("Nothing to do: " + tarFile.getAbsolutePath()) + " is up to date.", MSG_INFO);
        return;
      }
      log("Building tar: " + tarFile.getAbsolutePath(), MSG_INFO);
      TarOutputStream tOut = null;
      try {
        tOut =
            new TarOutputStream(
                compression.compress(new BufferedOutputStream(new FileOutputStream(tarFile))));
        tOut.setDebug(true);
        if (longFileMode.isTruncateMode()) {
          tOut.setLongFileMode(LONGFILE_TRUNCATE);
        } else if (longFileMode.isFailMode() || longFileMode.isOmitMode()) {
          tOut.setLongFileMode(LONGFILE_ERROR);
        } else {
          tOut.setLongFileMode(LONGFILE_GNU);
        }
        longWarningGiven = false;
        for (Enumeration e = filesets.elements(); e.hasMoreElements(); ) {
          tar(((TarFileSet) (e.nextElement())), tOut);
        }
        for (Enumeration e = resourceCollections.elements(); e.hasMoreElements(); ) {
          tar(((ResourceCollection) (e.nextElement())), tOut);
        }
      } catch (IOException ioe) {
        String msg = "Problem creating TAR: " + ioe.getMessage();
        throw new BuildException(msg, ioe, getLocation());
      } finally {
        if (tOut != null) {
          try {
            tOut.close();
          } catch (IOException e) {
          }
        }
      }
    } finally {
      filesets = savedFileSets;
    }
  }
}
