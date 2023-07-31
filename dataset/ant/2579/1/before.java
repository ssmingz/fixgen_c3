class PlaceHold {
  public void executeMain() throws BuildException {
    if ((((baseDir == null) && (filesets.size() == 0)) && (groupfilesets.size() == 0))
        && "zip".equals(archiveType)) {
      throw new BuildException(
          ("basedir attribute must be set, " + "or at least ") + "one fileset must be given!");
    }
    if (zipFile == null) {
      throw new BuildException(("You must specify the " + archiveType) + " file to create!");
    }
    if (zipFile.exists() && (!zipFile.isFile())) {
      throw new BuildException(zipFile + " is not a file.");
    }
    if (zipFile.exists() && (!zipFile.canWrite())) {
      throw new BuildException(zipFile + " is read-only.");
    }
    File renamedFile = null;
    addingNewFiles = true;
    if (doUpdate && (!zipFile.exists())) {
      doUpdate = false;
      log(("ignoring update attribute as " + archiveType) + " doesn't exist.", MSG_DEBUG);
    }
    for (int i = 0; i < groupfilesets.size(); i++) {
      log("Processing groupfileset ", MSG_VERBOSE);
      FileSet fs = ((FileSet) (groupfilesets.elementAt(i)));
      FileScanner scanner = fs.getDirectoryScanner(getProject());
      String[] files = scanner.getIncludedFiles();
      File basedir = scanner.getBasedir();
      for (int j = 0; j < files.length; j++) {
        log(("Adding file " + files[j]) + " to fileset", MSG_VERBOSE);
        ZipFileSet zf = new ZipFileSet();
        zf.setProject(getProject());
        zf.setSrc(new File(basedir, files[j]));
        filesets.addElement(zf);
        filesetsFromGroupfilesets.addElement(zf);
      }
    }
    Vector vfss = new Vector();
    if (baseDir != null) {
      FileSet fs = ((FileSet) (getImplicitFileSet().clone()));
      fs.setDir(baseDir);
      vfss.addElement(fs);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      vfss.addElement(fs);
    }
    FileSet[] fss = new FileSet[vfss.size()];
    vfss.copyInto(fss);
    boolean success = false;
    try {
      ArchiveState state = getResourcesToAdd(fss, zipFile, false);
      if (!state.isOutOfDate()) {
        return;
      }
      Resource[][] addThem = state.getResourcesToAdd();
      if (doUpdate) {
        renamedFile = FILE_UTILS.createTempFile("zip", ".tmp", zipFile.getParentFile());
        renamedFile.deleteOnExit();
        try {
          FILE_UTILS.rename(zipFile, renamedFile);
        } catch (SecurityException e) {
          throw new BuildException(
              ("Not allowed to rename old file (" + zipFile.getAbsolutePath())
                  + ") to temporary file");
        } catch (IOException e) {
          throw new BuildException(
              ("Unable to rename old file (" + zipFile.getAbsolutePath()) + ") to temporary file");
        }
      }
      String action = (doUpdate) ? "Updating " : "Building ";
      log(((action + archiveType) + ": ") + zipFile.getAbsolutePath());
      ZipOutputStream zOut = null;
      try {
        if (!skipWriting) {
          zOut = new ZipOutputStream(zipFile);
          zOut.setEncoding(encoding);
          zOut.setMethod(doCompress ? ZipOutputStream.DEFLATED : ZipOutputStream.STORED);
          zOut.setLevel(level);
        }
        initZipOutputStream(zOut);
        for (int i = 0; i < fss.length; i++) {
          if (addThem[i].length != 0) {
            addResources(fss[i], addThem[i], zOut);
          }
        }
        if (doUpdate) {
          addingNewFiles = false;
          ZipFileSet oldFiles = new ZipFileSet();
          oldFiles.setProject(getProject());
          oldFiles.setSrc(renamedFile);
          oldFiles.setDefaultexcludes(getImplicitFileSet().getDefaultexcludes());
          for (int i = 0; i < addedFiles.size(); i++) {
            PatternSet.NameEntry ne = oldFiles.createExclude();
            ne.setName(((String) (addedFiles.elementAt(i))));
          }
          DirectoryScanner ds = oldFiles.getDirectoryScanner(getProject());
          ((ZipScanner) (ds)).setEncoding(encoding);
          String[] f = ds.getIncludedFiles();
          Resource[] r = new Resource[f.length];
          for (int i = 0; i < f.length; i++) {
            r[i] = ds.getResource(f[i]);
          }
          if (!doFilesonly) {
            String[] d = ds.getIncludedDirectories();
            Resource[] dr = new Resource[d.length];
            for (int i = 0; i < d.length; i++) {
              dr[i] = ds.getResource(d[i]);
            }
            Resource[] tmp = r;
            r = new Resource[tmp.length + dr.length];
            System.arraycopy(dr, 0, r, 0, dr.length);
            System.arraycopy(tmp, 0, r, dr.length, tmp.length);
          }
          addResources(oldFiles, r, zOut);
        }
        zOut.setComment(comment);
        finalizeZipOutputStream(zOut);
        if (doUpdate) {
          if (!renamedFile.delete()) {
            log("Warning: unable to delete temporary file " + renamedFile.getName(), MSG_WARN);
          }
        }
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
      if (((!doUpdate) || (renamedFile != null)) && (!zipFile.delete())) {
        msg += " (and the archive is probably corrupt but I could not " + "delete it)";
      }
      if (doUpdate && (renamedFile != null)) {
        try {
          FILE_UTILS.rename(renamedFile, zipFile);
        } catch (IOException e) {
          msg += (" (and I couldn't rename the temporary file " + renamedFile.getName()) + " back)";
        }
      }
      throw new BuildException(msg, ioe, getLocation());
    } finally {
      cleanUp();
    }
  }
}
