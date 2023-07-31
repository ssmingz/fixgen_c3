class PlaceHold {
  public void test2bisButCaseInsensitive() {
    if (loginSuceeded) {
      if (changeRemoteDir(remoteTmpDir)) {
        FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
        ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
        ds.setIncludes(new String[] {"alpha/BETA/gamma/"});
        ds.setCaseSensitive(false);
        ds.scan();
        compareFiles(
            ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {"alpha/beta/gamma"});
      }
    }
  }
}
