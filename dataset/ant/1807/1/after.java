class PlaceHold {
  private void upload(List fileSet, String toSshUri) throws IOException, JSchException {
    String file = parseUri(toSshUri);
    Session session = null;
    try {
      session = openSession();
      List list = new ArrayList(fileSet.size());
      for (Iterator i = fileSet.iterator(); i.hasNext(); ) {
        FileSet set = ((FileSet) (i.next()));
        list.add(createDirectory(set));
      }
      ScpToMessage message = new ScpToMessage(session, list, file);
      message.setLogListener(this);
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
