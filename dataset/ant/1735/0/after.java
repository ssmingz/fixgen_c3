class PlaceHold {
  public void executeMain() throws BuildException {
    checkAttributesAndElements();
    File renamedFile = null;
    addingNewFiles = true;
    processDoUpdate();
    processGroupFilesets();
    Vector<ResourceCollection> vfss = new Vector<ResourceCollection>();
    if (baseDir != null) {
      FileSet fs = ((FileSet) (getImplicitFileSet().clone()));
      fs.setDir(baseDir);
      vfss.addElement(fs);
    }
    final int size = resources.size();
    for (int i = 0; i < size; i++) {
      ResourceCollection rc = ((ResourceCollection) (resources.elementAt(i)));
      vfss.addElement(rc);
    }
    ResourceCollection[] fss = new ResourceCollection[vfss.size()];
    vfss.copyInto(fss);
    boolean success = false;
    try {
      ArchiveState state = getResourcesToAdd(fss, zipFile, false);
      if (!state.isOutOfDate()) {
        return;
      }
      File parent = zipFile.getParentFile();
      if (((parent != null) && (!parent.isDirectory())) && (!parent.mkdirs())) {
        throw new BuildException(("Failed to create missing parent" + " directory for ") + zipFile);
      }
      updatedFile = true;
      if ((!zipFile.exists()) && state.isWithoutAnyResources()) {
        createEmptyZip(zipFile);
        return;
      }
      Resource[][] addThem = state.getResourcesToAdd();
      if (doUpdate) {
        renamedFile = renameFile();
      }
      String action = (doUpdate) ? "Updating " : "Building ";
      if (!skipWriting) {
        log(((action + archiveType) + ": ") + zipFile.getAbsolutePath());
      }
      ZipOutputStream zOut = null;
      try {
        if (!skipWriting) {
          zOut = new ZipOutputStream(zipFile);
          zOut.setEncoding(encoding);
          zOut.setUseLanguageEncodingFlag(useLanguageEncodingFlag);
          zOut.setCreateUnicodeExtraFields(createUnicodeExtraFields.getPolicy());
          zOut.setFallbackToUTF8(fallBackToUTF8);
          zOut.setMethod(doCompress ? ZipOutputStream.DEFLATED : ZipOutputStream.STORED);
          zOut.setLevel(level);
          zOut.setUseZip64(zip64Mode.getMode());
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
          oldFiles.setDefaultexcludes(false);
          final int addSize = addedFiles.size();
          for (int i = 0; i < addSize; i++) {
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
        if (zOut != null) {
          zOut.setComment(comment);
        }
        finalizeZipOutputStream(zOut);
        if (doUpdate) {
          if (!renamedFile.delete()) {
            log("Warning: unable to delete temporary file " + renamedFile.getName(), MSG_WARN);
          }
        }
        success = true;
      } finally {
        closeZout(zOut, success);
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
