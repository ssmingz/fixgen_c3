class PlaceHold {
  private void upload(String fromPath, String toSshUri) throws IOException, JSchException {
    String file = parseUri(toSshUri);
    Session session = null;
    try {
      session = openSession();
      ScpToMessage message = new ScpToMessage(session, getProject().resolveFile(fromPath), file);
      message.setLogListener(this);
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
