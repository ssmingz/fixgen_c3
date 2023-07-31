class PlaceHold {
  public void testFullPathMatchesCaseInsensitive() {
    if (loginSuceeded) {
      if (changeRemoteDir(remoteTmpDir)) {
        FTP.FTPDirectoryScanner ds = myFTPTask.newScanner(ftp);
        ds.setCaseSensitive(false);
        ds.setBasedir(new File(getProject().getBaseDir(), "tmp"));
        ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
        ds.scan();
        compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
      }
    }
  }
}
