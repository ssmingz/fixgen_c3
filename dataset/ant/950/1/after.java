class PlaceHold {
  private void upload(List fileSet, String toSshUri) throws IOException, JSchException {
    String file = parseUri(toSshUri);
    Session session = null;
    try {
      List list = new ArrayList(fileSet.size());
      for (Iterator i = fileSet.iterator(); i.hasNext(); ) {
        FileSet set = ((FileSet) (i.next()));
        Directory d = createDirectory(set);
        if (d != null) {
          list.add(d);
        }
      }
      if (!list.isEmpty()) {
        session = openSession();
        ScpToMessage message = new ScpToMessage(getVerbose(), session, list, file);
        message.setLogListener(this);
        message.execute();
      }
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
