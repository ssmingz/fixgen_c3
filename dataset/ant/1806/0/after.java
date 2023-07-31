class PlaceHold {
  @Test
  public void testAllowSymlinks() {
    Assume.assumeTrue("System does not support Symlinks", supportsSymlinks);
    Assume.assumeTrue(loginFailureMessage, loginSuceeded);
    Assume.assumeTrue("Could not change remote directory", changeRemoteDir(remoteTmpDir));
    buildRule.getProject().executeTarget("symlink-setup");
    FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
    ds.setBasedir(new File(buildRule.getProject().getBaseDir(), "tmp"));
    ds.setIncludes(new String[] {"alpha/beta/gamma/"});
    ds.setFollowSymlinks(true);
    ds.scan();
    compareFiles(
        ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {"alpha/beta/gamma"});
  }
}
