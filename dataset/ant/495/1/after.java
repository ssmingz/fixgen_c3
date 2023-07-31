class PlaceHold {
  public void execute() throws BuildException {
    checkAttributes();
    FTPClient ftp = null;
    try {
      log("Opening FTP connection to " + server, MSG_VERBOSE);
      ftp = new FTPClient();
      if (this.isConfigurationSet) {
        ftp = FTPConfigurator.configure(ftp, this);
      }
      ftp.connect(server, port);
      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
        throw new BuildException("FTP connection failed: " + ftp.getReplyString());
      }
      log("connected", MSG_VERBOSE);
      log("logging in to FTP server", MSG_VERBOSE);
      if (!ftp.login(userid, password)) {
        throw new BuildException("Could not login to FTP server");
      }
      log("login succeeded", MSG_VERBOSE);
      if (binary) {
        ftp.setFileType(IMAGE_FILE_TYPE);
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
          throw new BuildException("could not set transfer type: " + ftp.getReplyString());
        }
      } else {
        ftp.setFileType(ASCII_FILE_TYPE);
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
          throw new BuildException("could not set transfer type: " + ftp.getReplyString());
        }
      }
      if (passive) {
        log("entering passive mode", MSG_VERBOSE);
        ftp.enterLocalPassiveMode();
        if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
          throw new BuildException(
              ("could not enter into passive " + "mode: ") + ftp.getReplyString());
        }
      }
      if (this.initialSiteCommand != null) {
        RetryHandler h = new RetryHandler(this.retriesAllowed, this);
        final FTPClient lftp = ftp;
        executeRetryable(
            h,
            new Retryable() {
              public void execute() throws IOException {
                doSiteCommand(lftp, FTP.this.initialSiteCommand);
              }
            },
            "initial site command: " + this.initialSiteCommand);
      }
      if (umask != null) {
        RetryHandler h = new RetryHandler(this.retriesAllowed, this);
        final FTPClient lftp = ftp;
        executeRetryable(
            h,
            new Retryable() {
              public void execute() throws IOException {
                doSiteCommand(lftp, "umask " + umask);
              }
            },
            "umask " + umask);
      }
      if (action == MK_DIR) {
        RetryHandler h = new RetryHandler(this.retriesAllowed, this);
        final FTPClient lftp = ftp;
        executeRetryable(
            h,
            new Retryable() {
              public void execute() throws IOException {
                makeRemoteDir(lftp, remotedir);
              }
            },
            remotedir);
      } else if (action == SITE_CMD) {
        RetryHandler h = new RetryHandler(this.retriesAllowed, this);
        final FTPClient lftp = ftp;
        executeRetryable(
            h,
            new Retryable() {
              public void execute() throws IOException {
                doSiteCommand(lftp, FTP.this.siteCommand);
              }
            },
            "Site Command: " + this.siteCommand);
      } else {
        if (remotedir != null) {
          log("changing the remote directory", MSG_VERBOSE);
          ftp.changeWorkingDirectory(remotedir);
          if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
            throw new BuildException(
                ("could not change remote " + "directory: ") + ftp.getReplyString());
          }
        }
        if (newerOnly && timeDiffAuto) {
          timeDiffMillis = getTimeDiff(ftp);
        }
        log((ACTION_STRS[action] + " ") + ACTION_TARGET_STRS[action]);
        transferFiles(ftp);
      }
    } catch (IOException ex) {
      throw new BuildException("error during FTP transfer: " + ex, ex);
    } finally {
      if ((ftp != null) && ftp.isConnected()) {
        try {
          log("disconnecting", MSG_VERBOSE);
          ftp.logout();
          ftp.disconnect();
        } catch (IOException ex) {
        }
      }
    }
  }
}
