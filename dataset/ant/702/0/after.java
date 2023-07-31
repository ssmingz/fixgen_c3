class PlaceHold {
  private void executeCommand(Session session, String cmd, StringBuffer sb) throws BuildException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    TeeOutputStream tee = new TeeOutputStream(out, KeepAliveOutputStream.wrapSystemOut());
    InputStream istream = null;
    if (inputFile != null) {
      try {
        istream = new FileInputStream(inputFile);
      } catch (IOException e) {
        log((("Failed to read " + inputFile) + " because of: ") + e.getMessage(), MSG_WARN);
      }
    }
    if (inputProperty != null) {
      String inputData = getProject().getProperty(inputProperty);
      if (inputData != null) {
        istream = new ByteArrayInputStream(inputData.getBytes());
      }
    }
    try {
      final ChannelExec channel;
      session.setTimeout(((int) (maxwait)));
      channel = ((ChannelExec) (session.openChannel("exec")));
      channel.setCommand(cmd);
      channel.setOutputStream(tee);
      channel.setExtOutputStream(tee);
      if (istream != null) {
        channel.setInputStream(istream);
      }
      channel.connect();
      thread =
          new Thread() {
            public void run() {
              while (!channel.isClosed()) {
                if (thread == null) {
                  return;
                }
                try {
                  sleep(RETRY_INTERVAL);
                } catch (Exception e) {
                }
              }
            }
          };
      thread.start();
      thread.join(maxwait);
      if (thread.isAlive()) {
        thread = null;
        if (getFailonerror()) {
          throw new BuildException(TIMEOUT_MESSAGE);
        } else {
          log(TIMEOUT_MESSAGE, MSG_ERR);
        }
      } else {
        if (outputFile != null) {
          writeToFile(out.toString(), append, outputFile);
        }
        int ec = channel.getExitStatus();
        if (ec != 0) {
          String msg = "Remote command failed with exit status " + ec;
          if (getFailonerror()) {
            throw new BuildException(msg);
          } else {
            log(msg, MSG_ERR);
          }
        }
      }
    } catch (BuildException e) {
      throw e;
    } catch (JSchException e) {
      if (e.getMessage().indexOf("session is down") >= 0) {
        if (getFailonerror()) {
          throw new BuildException(TIMEOUT_MESSAGE, e);
        } else {
          log(TIMEOUT_MESSAGE, MSG_ERR);
        }
      } else if (getFailonerror()) {
        throw new BuildException(e);
      } else {
        log("Caught exception: " + e.getMessage(), MSG_ERR);
      }
    } catch (Exception e) {
      if (getFailonerror()) {
        throw new BuildException(e);
      } else {
        log("Caught exception: " + e.getMessage(), MSG_ERR);
      }
    } finally {
      sb.append(out.toString());
      FileUtils.close(istream);
    }
  }
}
