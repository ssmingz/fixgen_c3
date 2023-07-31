class PlaceHold {
  public void execute() throws BuildException {
    Path savedPath = path;
    String savedPathSep = pathSep;
    String savedDirSep = dirSep;
    try {
      if (isReference()) {
        path = new Path(getProject()).createPath();
        Object obj = refid.getReferencedObject(getProject());
        if (obj instanceof Path) {
          path.setRefid(refid);
        } else if (obj instanceof FileSet) {
          FileSet fs = ((FileSet) (obj));
          path.addFileset(fs);
        } else if (obj instanceof DirSet) {
          DirSet ds = ((DirSet) (obj));
          path.addDirset(ds);
        } else if (obj instanceof FileList) {
          FileList fl = ((FileList) (obj));
          path.addFilelist(fl);
        } else {
          throw new BuildException(
              ("'refid' does not refer to a " + "path, fileset, dirset, or ") + "filelist.");
        }
      }
      validateSetup();
      String fromDirSep = (onWindows) ? "\\" : "/";
      StringBuffer rslt = new StringBuffer();
      String[] elems = path.list();
      if (mapper != null) {
        FileNameMapper impl = mapper.getImplementation();
        List ret = new ArrayList();
        for (int i = 0; i < elems.length; ++i) {
          String[] mapped = impl.mapFileName(elems[i]);
          for (int m = 0; (mapped != null) && (m < mapped.length); ++m) {
            ret.add(mapped[m]);
          }
        }
        elems = ((String[]) (ret.toArray(new String[] {})));
      }
      for (int i = 0; i < elems.length; i++) {
        String elem = elems[i];
        elem = mapElement(elem);
        if (i != 0) {
          rslt.append(pathSep);
        }
        StringTokenizer stDirectory = new StringTokenizer(elem, fromDirSep, true);
        String token = null;
        while (stDirectory.hasMoreTokens()) {
          token = stDirectory.nextToken();
          if (fromDirSep.equals(token)) {
            rslt.append(dirSep);
          } else {
            rslt.append(token);
          }
        }
      }
      String value = rslt.toString();
      if (setonempty) {
        log((("Set property " + property) + " = ") + value, MSG_VERBOSE);
        getProject().setNewProperty(property, value);
      } else if (rslt.length() > 0) {
        log((("Set property " + property) + " = ") + value, MSG_VERBOSE);
        getProject().setNewProperty(property, value);
      }
    } finally {
      path = savedPath;
      dirSep = savedDirSep;
      pathSep = savedPathSep;
    }
  }
}
