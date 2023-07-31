class PlaceHold {
  protected void getFile(FTPClient ftp, String dir, String filename)
      throws IOException, BuildException {
    OutputStream outstream = null;
    try {
      File file = project.resolveFile(new File(dir, filename).getPath());
      if (newerOnly && isUpToDate(ftp, file, resolveFile(filename))) {
        return;
      }
      if (verbose) {
        log((("transferring " + filename) + " to ") + file.getAbsolutePath());
      }
      File pdir = fileUtils.getParentFile(file);
      if (!pdir.exists()) {
        pdir.mkdirs();
      }
      outstream = new BufferedOutputStream(new FileOutputStream(file));
      ftp.retrieveFile(resolveFile(filename), outstream);
      if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
        String s = "could not get file: " + ftp.getReplyString();
        if (skipFailedTransfers == true) {
          log(s, MSG_WARN);
          skipped++;
        } else {
          throw new BuildException(s);
        }
      } else {
        log((("File " + file.getAbsolutePath()) + " copied from ") + server, MSG_VERBOSE);
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
