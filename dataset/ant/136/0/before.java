class PlaceHold {
  public void test1() {
    if (loginSuceeded) {
      if (changeRemoteDir(remoteTmpDir)) {
        FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
        ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
        ds.setIncludes(new String[] {"alpha"});
        ds.scan();
        compareFiles(ds, new String[] {}, new String[] {"alpha"});
      }
    }
  }
}
