class PlaceHold {
  protected final void addResources(
      ResourceCollection rc, Resource[] resources, ZipOutputStream zOut) throws IOException {
    if (rc instanceof FileSet) {
      addResources(((FileSet) (rc)), resources, zOut);
      return;
    }
    for (int i = 0; i < resources.length; i++) {
      String name = resources[i].getName().replace(File.separatorChar, '/');
      if ("".equals(name)) {
        continue;
      }
      if (resources[i].isDirectory() && doFilesonly) {
        continue;
      }
      File base = null;
      FileProvider fp = ((FileProvider) (resources[i].as(FileProvider.class)));
      if (fp != null) {
        base = ResourceUtils.asFileResource(fp).getBaseDir();
      }
      if (resources[i].isDirectory()) {
        addDirectoryResource(
            resources[i], name, "", base, zOut, DEFAULT_DIR_MODE, DEFAULT_DIR_MODE);
      } else {
        addParentDirs(base, name, zOut, "", DEFAULT_DIR_MODE);
        if (fp != null) {
          File f = fp.getFile();
          zipFile(f, zOut, name, DEFAULT_FILE_MODE);
        } else {
          addResource(resources[i], name, "", zOut, DEFAULT_FILE_MODE, null);
        }
      }
    }
  }
}
