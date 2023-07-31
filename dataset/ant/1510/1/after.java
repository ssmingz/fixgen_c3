class PlaceHold {
  private void doMultipleTransfer() throws IOException, JSchException {
    final ChannelSftp channel = openSftpChannel();
    try {
      channel.connect();
      try {
        try {
          channel.stat(remotePath);
        } catch (final SftpException e) {
          if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
            channel.mkdir(remotePath);
            channel.chmod(getDirMode(), remotePath);
          } else {
            throw new JSchException(("failed to access remote dir '" + remotePath) + "'", e);
          }
        }
        channel.cd(remotePath);
      } catch (final SftpException e) {
        throw new JSchException((("Could not CD to '" + remotePath) + "' - ") + e.toString(), e);
      }
      Directory current = null;
      try {
        for (final Iterator i = directoryList.iterator(); i.hasNext(); ) {
          current = ((Directory) (i.next()));
          if (getVerbose()) {
            log("Sending directory " + current);
          }
          sendDirectory(channel, current);
        }
      } catch (final SftpException e) {
        String msg = "Error sending directory";
        if ((current != null) && (current.getDirectory() != null)) {
          msg += (" '" + current.getDirectory().getName()) + "'";
        }
        throw new JSchException(msg, e);
      }
    } finally {
      if (channel != null) {
        channel.disconnect();
      }
    }
  }
}
