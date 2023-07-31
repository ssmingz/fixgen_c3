class PlaceHold {
  private void upload(List fileSet, String toSshUri) throws IOException, JSchException {
    String[] toValues = parseUri(toSshUri);
    Session session = null;
    try {
      session = openSession(toValues[0], toValues[1], toValues[2], port);
      List list = new ArrayList(fileSet.size());
      for (Iterator i = fileSet.iterator(); i.hasNext(); ) {
        FileSet set = ((FileSet) (i.next()));
        list.add(createDirectory(set));
      }
      ScpToMessage message = new ScpToMessage(session, list, toValues[3]);
      message.setLogListener(this);
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
