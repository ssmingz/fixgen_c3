class PlaceHold {
  private void doSingleTransfer() throws IOException, JSchException {
    final String cmd = ("scp -t " + (getPreserveLastModified() ? "-p " : "")) + remotePath;
    final Channel channel = openExecChannel(cmd);
    try {
      final OutputStream out = channel.getOutputStream();
      final InputStream in = channel.getInputStream();
      channel.connect();
      waitForAck(in);
      sendFileToRemote(localFile, in, out);
    } finally {
      if (channel != null) {
        channel.disconnect();
      }
    }
  }
}
