class PlaceHold {
  protected void tarResource(Resource r, TarOutputStream tOut, String vPath, TarFileSet tarFileSet)
      throws IOException {
    if (!r.isExists()) {
      return;
    }
    if (tarFileSet != null) {
      String fullpath = tarFileSet.getFullpath(this.getProject());
      if (fullpath.length() > 0) {
        vPath = fullpath;
      } else {
        if (vPath.length() <= 0) {
          return;
        }
        String prefix = tarFileSet.getPrefix(this.getProject());
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
    }
    if (r.isDirectory() && (!vPath.endsWith("/"))) {
      vPath += "/";
    }
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
            getLocation());
      }
    }
    TarEntry te = new TarEntry(vPath);
    te.setModTime(r.getLastModified());
    if (r instanceof ArchiveResource) {
      ArchiveResource ar = ((ArchiveResource) (r));
      te.setMode(ar.getMode());
      if (r instanceof TarResource) {
        TarResource tr = ((TarResource) (r));
        te.setUserName(tr.getUserName());
        te.setUserId(tr.getUid());
        te.setGroupName(tr.getGroup());
        te.setGroupId(tr.getGid());
      }
    }
    if (!r.isDirectory()) {
      if (r.size() > TarConstants.MAXSIZE) {
        throw new BuildException(
            ((("Resource: " + r) + " larger than ") + TarConstants.MAXSIZE) + " bytes.");
      }
      te.setSize(r.getSize());
      if ((tarFileSet != null) && tarFileSet.hasFileModeBeenSet()) {
        te.setMode(tarFileSet.getMode());
      }
    } else if ((tarFileSet != null) && tarFileSet.hasDirModeBeenSet()) {
      te.setMode(tarFileSet.getDirMode(this.getProject()));
    }
    if (tarFileSet != null) {
      if (tarFileSet.hasUserNameBeenSet()) {
        te.setUserName(tarFileSet.getUserName());
      }
      if (tarFileSet.hasGroupBeenSet()) {
        te.setGroupName(tarFileSet.getGroup());
      }
      if (tarFileSet.hasUserIdBeenSet()) {
        te.setUserId(tarFileSet.getUid());
      }
      if (tarFileSet.hasGroupIdBeenSet()) {
        te.setGroupId(tarFileSet.getGid());
      }
    }
    InputStream in = null;
    try {
      tOut.putNextEntry(te);
      if (!r.isDirectory()) {
        in = r.getInputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int count = 0;
        do {
          tOut.write(buffer, 0, count);
          count = in.read(buffer, 0, buffer.length);
        } while (count != (-1));
      }
      tOut.closeEntry();
    } finally {
      FileUtils.close(in);
    }
  }
}
