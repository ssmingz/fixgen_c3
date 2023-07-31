class PlaceHold {
  private void doMultipleTransfer() throws IOException, JSchException {
    final Channel channel = openExecChannel("scp -r -d -t " + remotePath);
    try {
      final OutputStream out = channel.getOutputStream();
      final InputStream in = channel.getInputStream();
      channel.connect();
      waitForAck(in);
      for (final Iterator i = directoryList.iterator(); i.hasNext(); ) {
        final Directory current = ((Directory) (i.next()));
        sendDirectory(current, in, out);
      }
    } finally {
      if (channel != null) {
        channel.disconnect();
      }
    }
  }
}
