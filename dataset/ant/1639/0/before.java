class PlaceHold {
  public void testGetFollowSymlinksFalse() {
    if (!supportsSymlinks) {
      return;
    }
    if (!loginSuceeded) {
      return;
    }
    if (!changeRemoteDir(remoteTmpDir)) {
      return;
    }
    getProject().executeTarget("ftp-get-directory-no-symbolic-link");
    FileSet fsDestination =
        ((FileSet) (getProject().getReference("fileset-destination-without-selector")));
    DirectoryScanner dsDestination = fsDestination.getDirectoryScanner(getProject());
    dsDestination.scan();
    compareFiles(dsDestination, new String[] {}, new String[] {});
  }
}
