class PlaceHold {
  protected void tarFile(File file, TarOutputStream tOut, String vPath, TarFileSet tarFileSet)
      throws IOException {
    FileInputStream fIn = null;
    String fullpath = tarFileSet.getFullpath();
    if (fullpath.length() > 0) {
      vPath = fullpath;
    } else {
      if (vPath.length() <= 0) {
        return;
      }
      if (file.isDirectory() && (!vPath.endsWith("/"))) {
        vPath += "/";
      }
      String prefix = tarFileSet.getPrefix();
      if ((prefix.length() > 0) && (!prefix.endsWith("/"))) {
        prefix = prefix + "/";
      }
      vPath = prefix + vPath;
    }
    if (vPath.startsWith("/") && (!tarFileSet.getPreserveLeadingSlashes())) {
      int l = vPath.length();
      if (l <= 1) {
        return;
      }
      vPath = vPath.substring(1, l);
    }
    try {
      if (vPath.length() >= TarConstants.NAMELEN) {
        if (longFileMode.isOmitMode()) {
          log("Omitting: " + vPath, MSG_INFO);
          return;
        } else if (longFileMode.isWarnMode()) {
          log(
              ((("Entry: " + vPath) + " longer than ") + TarConstants.NAMELEN) + " characters.",
              MSG_WARN);
          if (!longWarningGiven) {
            log(
                "Resulting tar file can only be processed "
                    + "successfully by GNU compatible tar commands",
                MSG_WARN);
            longWarningGiven = true;
          }
        } else if (longFileMode.isFailMode()) {
          throw new BuildException(
              ((("Entry: " + vPath) + " longer than ") + TarConstants.NAMELEN) + "characters.",
              location);
        }
      }
      TarEntry te = new TarEntry(vPath);
      te.setModTime(file.lastModified());
      if (!file.isDirectory()) {
        te.setSize(file.length());
        te.setMode(tarFileSet.getMode());
      }
      te.setUserName(tarFileSet.getUserName());
      te.setGroupName(tarFileSet.getGroup());
      tOut.putNextEntry(te);
      if (!file.isDirectory()) {
        fIn = new FileInputStream(file);
        byte[] buffer = new byte[8 * 1024];
        int count = 0;
        do {
          tOut.write(buffer, 0, count);
          count = fIn.read(buffer, 0, buffer.length);
        } while (count != (-1));
      }
      tOut.closeEntry();
    } finally {
      if (fIn != null) {
        fIn.close();
      }
    }
  }
}
