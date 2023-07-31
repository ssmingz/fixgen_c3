class PlaceHold {
  private void upload(final String fromPath, final String toSshUri)
      throws IOException, JSchException {
    final String file = parseUri(toSshUri);
    Session session = null;
    try {
      session = openSession();
      ScpToMessage message = null;
      if (!isSftp) {
        message =
            new ScpToMessage(
                getVerbose(),
                session,
                getProject().resolveFile(fromPath),
                file,
                preserveLastModified);
      } else {
        message =
            new ScpToMessageBySftp(getVerbose(), session, getProject().resolveFile(fromPath), file);
      }
      message.setLogListener(this);
      if (fileMode != null) {
        message.setFileMode(fileMode.intValue());
      }
      if (dirMode != null) {
        message.setDirMode(dirMode.intValue());
      }
      message.execute();
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
