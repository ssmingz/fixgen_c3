class PlaceHold {
  private void sendDirectoryToRemote(final ChannelSftp channel, final Directory directory)
      throws IOException, SftpException {
    final String dir = directory.getDirectory().getName();
    try {
      channel.stat(dir);
    } catch (final SftpException e) {
      if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
        channel.mkdir(dir);
      }
    }
    channel.cd(dir);
    sendDirectory(channel, directory);
    channel.cd("..");
  }
}
