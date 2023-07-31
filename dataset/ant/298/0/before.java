class PlaceHold {
  protected ArchiveState getNonFileSetResourcesToAdd(
      ResourceCollection[] rcs, File zipFile, boolean needsUpdate) throws BuildException {
    Resource[][] initialResources = grabNonFileSetResources(rcs);
    if (isEmpty(initialResources)) {
      return new ArchiveState(needsUpdate, initialResources);
    }
    if (!zipFile.exists()) {
      return new ArchiveState(true, initialResources);
    }
    if (needsUpdate && (!doUpdate)) {
      return new ArchiveState(true, initialResources);
    }
    Resource[][] newerResources = new Resource[rcs.length][];
    for (int i = 0; i < rcs.length; i++) {
      if (initialResources[i].length == 0) {
        newerResources[i] = new Resource[] {};
        continue;
      }
      for (int j = 0; j < initialResources[i].length; j++) {
        FileProvider fp = ((FileProvider) (initialResources[i][j].as(FileProvider.class)));
        if ((fp != null) && zipFile.equals(fp.getFile())) {
          throw new BuildException("A zip file cannot include " + "itself", getLocation());
        }
      }
      Resource[] rs = initialResources[i];
      rs = selectFileResources(rs);
      newerResources[i] =
          ResourceUtils.selectOutOfDateSources(this, rs, new IdentityMapper(), getZipScanner());
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
