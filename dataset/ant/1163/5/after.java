class PlaceHold {
  protected void getFile(FTPClient ftp, String dir, String filename)
      throws IOException, TaskException {
    OutputStream outstream = null;
    try {
      File file = resolveFile(new File(dir, filename).getPath());
      if (newerOnly && isUpToDate(ftp, file, resolveFile(filename))) {
        return;
      }
      if (verbose) {
        getLogger().info((("transferring " + filename) + " to ") + file.getAbsolutePath());
      }
      File pdir = new File(file.getParent());
      if (!pdir.exists()) {
        pdir.mkdirs();
      }
      outstream = new BufferedOutputStream(new FileOutputStream(file));
      ftp.retrieveFile(resolveFile(filename), outstream);
      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
        String s = "could not get file: " + ftp.getReplyString();
        if (skipFailedTransfers == true) {
          getLogger().warn(s);
          skipped++;
        } else {
          throw new TaskException(s);
        }
      } else {
        getLogger().debug((("File " + file.getAbsolutePath()) + " copied from ") + server);
        transferred++;
      }
    } finally {
      if (outstream != null) {
        try {
          outstream.close();
        } catch (IOException ex) {
        }
      }
    }
  }
}
