class PlaceHold {
  private void download(String fromSshUri, String toPath) throws JSchException, IOException {
    String file = parseUri(fromSshUri);
    Session session = null;
    try {
      session = openSession();
      ScpFromMessage message =
          new ScpFromMessage(session, file, new File(toPath), fromSshUri.endsWith("*"));
      log("Receiving file: " + file);
      message.setLogListener(this);
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
