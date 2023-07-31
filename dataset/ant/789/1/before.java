class PlaceHold {
  public int executeInVM(CommandlineJava commandline) throws BuildException {
    JDepend jdepend;
    if ("xml".equals(format)) {
      jdepend = new JDepend();
    } else {
      jdepend = new JDepend();
    }
    FileWriter fw = null;
    if (getOutputFile() != null) {
      try {
        fw = new FileWriter(getOutputFile().getPath());
      } catch (IOException e) {
        String msg = "JDepend Failed when creating the output file: " + e.getMessage();
        log(msg);
        throw new BuildException(msg);
      }
      jdepend.setWriter(new PrintWriter(fw));
      log("Output to be stored in " + getOutputFile().getPath());
    }
    try {
      if (getClassespath() != null) {
        String[] cP = getClassespath().list();
        for (int i = 0; i < cP.length; i++) {
          File f = new File(cP[i]);
          if ((!f.exists()) || (!f.isDirectory())) {
            String msg =
                (("\"" + f.getPath()) + "\" does not represent a valid")
                    + " directory. JDepend would fail.";
            log(msg);
            throw new BuildException(msg);
          }
          try {
            jdepend.addDirectory(f.getPath());
          } catch (IOException e) {
            String msg = "JDepend Failed when adding a class directory: " + e.getMessage();
            log(msg);
            throw new BuildException(msg);
          }
        }
      } else if (getSourcespath() != null) {
        String[] sP = getSourcespath().list();
        for (int i = 0; i < sP.length; i++) {
          File f = new File(sP[i]);
          if ((!f.exists()) || (!f.isDirectory())) {
            String msg =
                (("\"" + f.getPath()) + "\" does not represent a valid")
                    + " directory. JDepend would fail.";
            log(msg);
            throw new BuildException(msg);
          }
          try {
            jdepend.addDirectory(f.getPath());
          } catch (IOException e) {
            String msg = "JDepend Failed when adding a source directory: " + e.getMessage();
            log(msg);
            throw new BuildException(msg);
          }
        }
      }
      String[] patterns = defaultPatterns.getExcludePatterns(getProject());
      if ((patterns != null) && (patterns.length > 0)) {
        if (setFilter != null) {
          Vector v = new Vector();
          for (int i = 0; i < patterns.length; i++) {
            v.addElement(patterns[i]);
          }
          try {
            Object o = packageFilterC.newInstance(new Object[] {v});
            setFilter.invoke(jdepend, new Object[] {o});
          } catch (Throwable e) {
            log("excludes will be ignored as JDepend doesn't like me: " + e.getMessage(), MSG_WARN);
          }
        } else {
          log("Sorry, your version of JDepend doesn't support excludes", MSG_WARN);
        }
      }
      jdepend.analyze();
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (Throwable t) {
        }
      }
    }
    return SUCCESS;
  }
}
