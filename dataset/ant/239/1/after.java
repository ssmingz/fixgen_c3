class PlaceHold {
  protected int transferFiles(final FTPClient ftp, FileSet fs) throws IOException, BuildException {
    DirectoryScanner ds;
    if (action == SEND_FILES) {
      ds = fs.getDirectoryScanner(getProject());
    } else {
      ds = new FTPDirectoryScanner(ftp);
      fs.setupDirectoryScanner(ds, getProject());
      ds.setFollowSymlinks(fs.isFollowSymlinks());
      ds.scan();
    }
    String[] dsfiles = null;
    if (action == RM_DIR) {
      dsfiles = ds.getIncludedDirectories();
    } else {
      dsfiles = ds.getIncludedFiles();
    }
    String dir = null;
    if ((ds.getBasedir() == null) && ((action == SEND_FILES) || (action == GET_FILES))) {
      throw new BuildException("the dir attribute must be set for send " + "and get actions");
    } else if ((action == SEND_FILES) || (action == GET_FILES)) {
      dir = ds.getBasedir().getAbsolutePath();
    }
    BufferedWriter bw = null;
    try {
      if (action == LIST_FILES) {
        File pd = listing.getParentFile();
        if (!pd.exists()) {
          pd.mkdirs();
        }
        bw = new BufferedWriter(new FileWriter(listing));
      }
      RetryHandler h = new RetryHandler(this.retriesAllowed, this);
      if (action == RM_DIR) {
        for (int i = dsfiles.length - 1; i >= 0; i--) {
          final String dsfile = dsfiles[i];
          executeRetryable(
              h,
              new Retryable() {
                public void execute() throws IOException {
                  rmDir(ftp, dsfile);
                }
              },
              dsfile);
        }
      } else {
        final BufferedWriter fbw = bw;
        final String fdir = dir;
        if (this.newerOnly) {
          this.granularityMillis = this.timestampGranularity.getMilliseconds(action);
        }
        for (int i = 0; i < dsfiles.length; i++) {
          final String dsfile = dsfiles[i];
          executeRetryable(
              h,
              new Retryable() {
                public void execute() throws IOException {
                  switch (action) {
                    case SEND_FILES:
                      sendFile(ftp, fdir, dsfile);
                      break;
                    case GET_FILES:
                      getFile(ftp, fdir, dsfile);
                      break;
                    case DEL_FILES:
                      delFile(ftp, dsfile);
                      break;
                    case LIST_FILES:
                      listFile(ftp, fbw, dsfile);
                      break;
                    case CHMOD:
                      doSiteCommand(ftp, (("chmod " + chmod) + " ") + resolveFile(dsfile));
                      transferred++;
                      break;
                    default:
                      throw new BuildException("unknown ftp action " + action);
                  }
                }
              },
              dsfile);
        }
      }
    } finally {
      FileUtils.close(bw);
    }
    return dsfiles.length;
  }
}
