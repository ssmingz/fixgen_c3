class PlaceHold {
  protected void sendFile(FTPClient ftp, String dir, String filename)
      throws IOException, TaskException {
    InputStream instream = null;
    try {
      File file = resolveFile(new File(dir, filename).getPath());
      if (newerOnly && isUpToDate(ftp, file, resolveFile(filename))) {
        return;
      }
      if (verbose) {
        getLogger().info("transferring " + file.getAbsolutePath());
      }
      instream = new BufferedInputStream(new FileInputStream(file));
      createParents(ftp, filename);
      ftp.storeFile(resolveFile(filename), instream);
      boolean success = FTPReply.isPositiveCompletion(ftp.getReplyCode());
      if (!success) {
        String s = "could not put file: " + ftp.getReplyString();
        if (skipFailedTransfers == true) {
          getLogger().warn(s);
          skipped++;
        } else {
          throw new TaskException(s);
        }
      } else {
        getLogger().debug((("File " + file.getAbsolutePath()) + " copied to ") + server);
        transferred++;
      }
    } finally {
      if (instream != null) {
        try {
          instream.close();
        } catch (IOException ex) {
        }
      }
    }
  }
}
