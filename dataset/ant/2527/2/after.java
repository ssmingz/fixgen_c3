class PlaceHold {
  public void recreate() throws BuildException {
    try {
      if (fileSets.isEmpty()) {
        handleError("File set identifying link file(s) " + "required for action recreate");
        return;
      }
      Properties links = loadLinks(fileSets);
      for (Iterator kitr = links.keySet().iterator(); kitr.hasNext(); ) {
        String lnk = ((String) (kitr.next()));
        String res = links.getProperty(lnk);
        try {
          File test = new File(lnk);
          if (!FILE_UTILS.isSymbolicLink(null, lnk)) {
            doLink(res, lnk);
          } else if (!test.getCanonicalPath().equals(new File(res).getCanonicalPath())) {
            deleteSymlink(lnk, this);
            doLink(res, lnk);
          }
        } catch (IOException ioe) {
          handleError("IO exception while creating link");
        }
      }
    } finally {
      setDefaults();
    }
  }
}
