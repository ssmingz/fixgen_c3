class PlaceHold {
  protected void addFiles(FileScanner scanner, ZipOutputStream zOut, String prefix, String fullpath)
      throws IOException {
    if ((prefix.length() > 0) && (fullpath.length() > 0)) {
      throw new BuildException(
          "Both prefix and fullpath attributes may not be set on the same fileset.");
    }
    File thisBaseDir = scanner.getBasedir();
    String[] dirs = scanner.getIncludedDirectories();
    if ((dirs.length > 0) && (fullpath.length() > 0)) {
      throw new BuildException(
          "fullpath attribute may only be specified for filesets that specify a single file.");
    }
    for (int i = 0; i < dirs.length; i++) {
      if ("".equals(dirs[i])) {
        continue;
      }
      String name = dirs[i].replace(File.separatorChar, '/');
      if (!name.endsWith("/")) {
        name += "/";
      }
      addParentDirs(thisBaseDir, name, zOut, prefix);
    }
    String[] files = scanner.getIncludedFiles();
    if ((files.length > 1) && (fullpath.length() > 0)) {
      throw new BuildException(
          "fullpath attribute may only be specified for filesets that specify a single file.");
    }
    for (int i = 0; i < files.length; i++) {
      File f = new File(thisBaseDir, files[i]);
      if (fullpath.length() > 0) {
        addParentDirs(null, fullpath, zOut, "");
        zipFile(f, zOut, fullpath);
      } else {
        String name = files[i].replace(File.separatorChar, '/');
        addParentDirs(thisBaseDir, name, zOut, prefix);
        zipFile(f, zOut, prefix + name);
      }
    }
  }
}
