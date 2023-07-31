class PlaceHold {
  public void execute() throws TaskException {
    if (P4View != null) {
      addCmd = P4View;
    }
    P4CmdOpts = (m_changelist > 0) ? "-c " + m_changelist : "";
    StringBuffer filelist = new StringBuffer();
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      String[] srcFiles = ds.getIncludedFiles();
      if (srcFiles != null) {
        for (int j = 0; j < srcFiles.length; j++) {
          File f = new File(ds.getBasedir(), srcFiles[j]);
          filelist.append(" ").append('"').append(f.getAbsolutePath()).append('"');
          if (filelist.length() > m_cmdLength) {
            execP4Add(filelist);
            filelist.setLength(0);
          }
        }
        if (filelist.length() > 0) {
          execP4Add(filelist);
        }
      } else {
        log("No files specified to add!", MSG_WARN);
      }
    }
  }
}
