class PlaceHold {
  public void execute() throws TaskException {
    if ((null == jar) && (null == filesets)) {
      throw new TaskException("jar must be set through jar attribute or nested filesets");
    }
    if (null != jar) {
      doOneJar(jar, signedjar);
      return;
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        DirectoryScanner ds = fs.getDirectoryScanner(getProject());
        String[] jarFiles = ds.getIncludedFiles();
        for (int j = 0; j < jarFiles.length; j++) {
          doOneJar(new File(fs.getDir(getProject()), jarFiles[j]), null);
        }
      }
    }
  }
}
