class PlaceHold {
  public void testFullPathMatchesCaseSensitive() {
    if (loginSuceeded) {
      if (changeRemoteDir(remoteTmpDir)) {
        FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
        ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
        ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
        ds.scan();
        compareFiles(ds, new String[] {}, new String[] {});
      }
    }
  }
}
