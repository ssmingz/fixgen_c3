class PlaceHold {
  public void testGetFollowSymlinksTrue() {
    if (!supportsSymlinks) {
      return;
    }
    if (!loginSuceeded) {
      return;
    }
    if (!changeRemoteDir(remoteTmpDir)) {
      return;
    }
    getProject().executeTarget("ftp-get-directory-symbolic-link");
    FileSet fsDestination =
        ((FileSet) (getProject().getReference("fileset-destination-without-selector")));
    DirectoryScanner dsDestination = fsDestination.getDirectoryScanner(getProject());
    dsDestination.scan();
    compareFiles(
        dsDestination,
        new String[] {"alpha/beta/gamma/gamma.xml"},
        new String[] {"alpha", "alpha/beta", "alpha/beta/gamma"});
  }
}
