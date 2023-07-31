class PlaceHold {
  private void upload(final List<ResourceCollection> rcs, final String toSshUri)
      throws IOException, JSchException {
    final String file = parseUri(toSshUri);
    Session session = null;
    try {
      final List<Directory> list = new ArrayList<Directory>(rcs.size());
      for (final Iterator<ResourceCollection> i = rcs.iterator(); i.hasNext(); ) {
        final ResourceCollection rc = ((ResourceCollection) (i.next()));
        if ((rc instanceof FileSet) && rc.isFilesystemOnly()) {
          FileSet fs = ((FileSet) (rc));
          final Directory d = createDirectory(fs);
          if (d != null) {
            list.add(d);
          }
        } else {
          List<Directory> ds = createDirectoryCollection(rc);
          if (ds != null) {
            list.addAll(ds);
          }
        }
      }
      if (!list.isEmpty()) {
        session = openSession();
        ScpToMessage message = null;
        if (!isSftp) {
          message = new ScpToMessage(getVerbose(), session, list, file, preserveLastModified);
        } else {
          message = new ScpToMessageBySftp(getVerbose(), session, list, file);
        }
        message.setLogListener(this);
        if (fileMode != null) {
          message.setFileMode(fileMode.intValue());
        }
        if (dirMode != null) {
          message.setDirMode(dirMode.intValue());
        }
        message.execute();
      }
    } finally {
      if (session != null) {
        session.disconnect();
      }
    }
  }
}
