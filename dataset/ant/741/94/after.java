class PlaceHold {
  protected void sendFile(FTPClient ftp, String dir, String filename)
      throws IOException, BuildException {
    InputStream instream = null;
    try {
      File file = getProject().resolveFile(new File(dir, filename).getPath());
      if (newerOnly && isUpToDate(ftp, file, resolveFile(filename))) {
        return;
      }
      if (verbose) {
        log("transferring " + file.getAbsolutePath());
      }
      instream = new BufferedInputStream(new FileInputStream(file));
      createParents(ftp, filename);
      ftp.storeFile(resolveFile(filename), instream);
      boolean success = FTPReply.isPositiveCompletion(ftp.getReplyCode());
      if (!success) {
        String s = "could not put file: " + ftp.getReplyString();
        if (skipFailedTransfers == true) {
          log(s, MSG_WARN);
          skipped++;
        } else {
          throw new BuildException(s);
        }
      } else {
        if (chmod != null) {
          doSiteCommand(ftp, (("chmod " + chmod) + " ") + filename);
        }
        log((("File " + file.getAbsolutePath()) + " copied to ") + server, MSG_VERBOSE);
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
