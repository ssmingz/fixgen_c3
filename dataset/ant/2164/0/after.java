class PlaceHold {
  public void execute() throws TaskException {
    if (((m_baseDir == null) && (m_filesets.size() == 0)) && "zip".equals(m_archiveType)) {
      final String message =
          "basedir attribute must be set, or at least " + "one fileset must be given!";
      throw new TaskException(message);
    }
    if (m_file == null) {
      final String message = ("You must specify the " + m_archiveType) + " file to create!";
      throw new TaskException(message);
    }
    File renamedFile = null;
    m_addingNewFiles = true;
    m_update = m_update && m_file.exists();
    if (m_update) {
      try {
        renamedFile = File.createTempFile("zip", ".tmp", m_file.getParentFile());
      } catch (final IOException ioe) {
        throw new TaskException(ioe.toString(), ioe);
      }
      try {
        if (!m_file.renameTo(renamedFile)) {
          throw new TaskException("Unable to rename old file to temporary file");
        }
      } catch (SecurityException e) {
        throw new TaskException("Not allowed to rename old file to temporary file");
      }
    }
    ArrayList dss = new ArrayList();
    if (m_baseDir != null) {
      dss.add(getDirectoryScanner(m_baseDir));
    }
    for (int i = 0; i < m_filesets.size(); i++) {
      FileSet fs = ((FileSet) (m_filesets.get(i)));
      dss.add(ScannerUtil.getDirectoryScanner(fs));
    }
    int dssSize = dss.size();
    FileScanner[] scanners = new FileScanner[dssSize];
    scanners = ((FileScanner[]) (dss.toArray(scanners)));
    if (isUpToDate(scanners, m_file)) {
      return;
    }
    String action = (m_update) ? "Updating " : "Building ";
    getLogger().info(((action + m_archiveType) + ": ") + m_file.getAbsolutePath());
    boolean success = false;
    try {
      ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(m_file));
      zOut.setEncoding(m_encoding);
      try {
        if (m_compress) {
          zOut.setMethod(DEFLATED);
        } else {
          zOut.setMethod(STORED);
        }
        initZipOutputStream(zOut);
        if (m_baseDir != null) {
          addFiles(getDirectoryScanner(m_baseDir), zOut, "", "");
        }
        addFiles(m_filesets, zOut);
        if (m_update) {
          m_addingNewFiles = false;
          ZipFileSet oldFiles = new ZipFileSet();
          oldFiles.setSrc(renamedFile);
          StringBuffer exclusionPattern = new StringBuffer();
          for (int i = 0; i < m_addedFiles.size(); i++) {
            if (i != 0) {
              exclusionPattern.append(",");
            }
            exclusionPattern.append(((String) (m_addedFiles.get(i))));
          }
          oldFiles.setExcludes(exclusionPattern.toString());
          ArrayList tmp = new ArrayList();
          tmp.add(oldFiles);
          addFiles(tmp, zOut);
        }
        finalizeZipOutputStream(zOut);
        success = true;
      } finally {
        try {
          if (zOut != null) {
            zOut.close();
          }
        } catch (IOException ex) {
          if (success) {
            throw ex;
          }
        }
      }
    } catch (IOException ioe) {
      String msg = (("Problem creating " + m_archiveType) + ": ") + ioe.getMessage();
      if (!m_file.delete()) {
        msg += " (and the archive is probably corrupt but I could not delete it)";
      }
      if (m_update) {
        if (!renamedFile.renameTo(m_file)) {
          msg += (" (and I couldn't rename the temporary file " + renamedFile.getName()) + " back)";
        }
      }
      throw new TaskException(msg, ioe);
    }
    if (success && m_update) {
      if (!renamedFile.delete()) {
        final String message = "Warning: unable to delete temporary file " + renamedFile.getName();
        getLogger().warn(message);
      }
    }
  }
}
