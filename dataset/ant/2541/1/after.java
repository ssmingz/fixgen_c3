class PlaceHold {
  protected void makeRemoteDir(FTPClient ftp, String dir) throws IOException, BuildException {
    String workingDirectory = ftp.printWorkingDirectory();
    if (verbose) {
      log("Creating directory: " + dir);
    }
    if (dir.indexOf("/") == 0) {
      ftp.changeWorkingDirectory("/");
    }
    String subdir = "";
    StringTokenizer st = new StringTokenizer(dir, "/");
    while (st.hasMoreTokens()) {
      subdir = st.nextToken();
      log("Checking " + subdir, MSG_DEBUG);
      if (!ftp.changeWorkingDirectory(subdir)) {
        if (!ftp.makeDirectory(subdir)) {
          int rc = ftp.getReplyCode();
          if (!(ignoreNoncriticalErrors
              && (((rc == FTPReply.CODE_550) || (rc == FTPReply.CODE_553)) || (rc == CODE_521)))) {
            throw new BuildException("could not create directory: " + ftp.getReplyString());
          }
          if (verbose) {
            log("Directory already exists");
          }
        } else {
          if (verbose) {
            log("Directory created OK");
          }
          ftp.changeWorkingDirectory(subdir);
        }
      }
    }
    if (workingDirectory != null) {
      ftp.changeWorkingDirectory(workingDirectory);
    }
  }
}
