class PlaceHold {
  @Test
  public void testFullPathMatchesCaseInsensitive() {
    Assume.assumeTrue(loginFailureMessage, loginSuceeded);
    Assume.assumeTrue("Could not change remote directory", changeRemoteDir(remoteTmpDir));
    FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
    ds.setCaseSensitive(false);
    ds.setBasedir(new File(buildRule.getProject().getBaseDir(), "tmp"));
    ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
    ds.scan();
    compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
  }
}
