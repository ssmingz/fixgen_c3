class PlaceHold {
  public void test2() {
    if (loginSuceeded) {
      if (changeRemoteDir(remoteTmpDir)) {
        FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
        ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
        ds.setIncludes(new String[] {"alpha/"});
        ds.scan();
        compareFiles(
            ds,
            new String[] {"alpha/beta/beta.xml", "alpha/beta/gamma/gamma.xml"},
            new String[] {"alpha", "alpha/beta", "alpha/beta/gamma"});
      }
    }
  }
}
