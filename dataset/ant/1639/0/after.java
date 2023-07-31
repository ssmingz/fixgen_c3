class PlaceHold {
  @Test
  public void testGetFollowSymlinksFalse() {
    Assume.assumeTrue("System does not support Symlinks", supportsSymlinks);
    Assume.assumeTrue(loginFailureMessage, loginSuceeded);
    Assume.assumeTrue("Could not change remote directory", changeRemoteDir(remoteTmpDir));
    buildRule.getProject().executeTarget("ftp-get-directory-no-symbolic-link");
    FileSet fsDestination =
        ((FileSet) (buildRule.getProject().getReference("fileset-destination-without-selector")));
    DirectoryScanner dsDestination = fsDestination.getDirectoryScanner(buildRule.getProject());
    dsDestination.scan();
    compareFiles(dsDestination, new String[] {}, new String[] {});
  }
}
