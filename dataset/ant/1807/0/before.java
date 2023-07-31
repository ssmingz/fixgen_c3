class PlaceHold {
  private void upload(String fromPath, String toSshUri) throws IOException, JSchException {
    String[] toValues = parseUri(toSshUri);
    Session session = null;
    try {
      session = openSession(toValues[0], toValues[1], toValues[2], port);
      ScpToMessage message = new ScpToMessage(session, new File(fromPath), toValues[3]);
      message.setLogListener(this);
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
