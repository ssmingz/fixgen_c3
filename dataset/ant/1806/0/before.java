class PlaceHold {
  public void testAllowSymlinks() {
    if (!supportsSymlinks) {
      return;
    }
    if (!loginSuceeded) {
      return;
    }
    if (!changeRemoteDir(remoteTmpDir)) {
      return;
    }
    getProject().executeTarget("symlink-setup");
    FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
    ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
    ds.setIncludes(new String[] {"alpha/beta/gamma/"});
    ds.setFollowSymlinks(true);
    ds.scan();
    compareFiles(
        ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {"alpha/beta/gamma"});
  }
}
