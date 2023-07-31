class PlaceHold {
  protected ArchiveState getResourcesToAdd(FileSet[] filesets, File zipFile, boolean needsUpdate)
      throws BuildException {
    Resource[][] initialResources = grabResources(filesets);
    if (isEmpty(initialResources)) {
      if (needsUpdate && doUpdate) {
        return new ArchiveState(true, initialResources);
      }
      if (emptyBehavior.equals("skip")) {
        if (doUpdate) {
          logOnFirstPass(
              (((archiveType + " archive ") + zipFile) + " not updated because no new files were")
                  + " included.",
              MSG_VERBOSE);
        } else {
          logOnFirstPass(
              ((("Warning: skipping " + archiveType) + " archive ") + zipFile)
                  + " because no files were included.",
              MSG_WARN);
        }
      } else if (emptyBehavior.equals("fail")) {
        throw new BuildException(
            ((("Cannot create " + archiveType) + " archive ") + zipFile)
                + ": no files were included.",
            getLocation());
      } else if (!zipFile.exists()) {
        needsUpdate = true;
      }
      return new ArchiveState(needsUpdate, initialResources);
    }
    if (!zipFile.exists()) {
      return new ArchiveState(true, initialResources);
    }
    if (needsUpdate && (!doUpdate)) {
      return new ArchiveState(true, initialResources);
    }
    Resource[][] newerResources = new Resource[filesets.length][];
    for (int i = 0; i < filesets.length; i++) {
      if ((!(fileset instanceof ZipFileSet))
          || (((ZipFileSet) (fileset)).getSrc(getProject()) == null)) {
        File base = filesets[i].getDir(getProject());
        for (int j = 0; j < initialResources[i].length; j++) {
          File resourceAsFile = FILE_UTILS.resolveFile(base, initialResources[i][j].getName());
          if (resourceAsFile.equals(zipFile)) {
            throw new BuildException("A zip file cannot include " + "itself", getLocation());
          }
        }
      }
    }
    for (int i = 0; i < filesets.length; i++) {
      if (initialResources[i].length == 0) {
        newerResources[i] = new Resource[] {};
        continue;
      }
      FileNameMapper myMapper = new IdentityMapper();
      if (filesets[i] instanceof ZipFileSet) {
        ZipFileSet zfs = ((ZipFileSet) (filesets[i]));
        if ((zfs.getFullpath(getProject()) != null)
            && (!zfs.getFullpath(getProject()).equals(""))) {
          MergingMapper fm = new MergingMapper();
          fm.setTo(zfs.getFullpath(getProject()));
          myMapper = fm;
        } else if ((zfs.getPrefix(getProject()) != null)
            && (!zfs.getPrefix(getProject()).equals(""))) {
          GlobPatternMapper gm = new GlobPatternMapper();
          gm.setFrom("*");
          String prefix = zfs.getPrefix(getProject());
          if ((!prefix.endsWith("/")) && (!prefix.endsWith("\\"))) {
            prefix += "/";
          }
          gm.setTo(prefix + "*");
          myMapper = gm;
        }
      }
      Resource[] resources = initialResources[i];
      resources = selectFileResources(resources);
      newerResources[i] =
          ResourceUtils.selectOutOfDateSources(this, resources, myMapper, getZipScanner());
      needsUpdate = needsUpdate || (newerResources[i].length > 0);
      if (needsUpdate && (!doUpdate)) {
        break;
      }
    }
    if (needsUpdate && (!doUpdate)) {
      return new ArchiveState(true, initialResources);
    }
    return new ArchiveState(needsUpdate, newerResources);
  }
}
