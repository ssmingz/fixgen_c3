class PlaceHold {
  public void execute() throws BuildException {
    Hashtable allProps = new Hashtable();
    if ((inFile == null) && (propertySets.size() == 0)) {
      allProps.putAll(getProject().getProperties());
    } else if (inFile != null) {
      if (inFile.exists() && inFile.isDirectory()) {
        String message = "srcfile is a directory!";
        if (failonerror) {
          throw new BuildException(message, getLocation());
        } else {
          log(message, MSG_ERR);
        }
        return;
      }
      if (inFile.exists() && (!inFile.canRead())) {
        String message = "Can not read from the specified srcfile!";
        if (failonerror) {
          throw new BuildException(message, getLocation());
        } else {
          log(message, MSG_ERR);
        }
        return;
      }
      FileInputStream in = null;
      try {
        in = new FileInputStream(inFile);
        Properties props = new Properties();
        props.load(in);
        allProps.putAll(props);
      } catch (FileNotFoundException fnfe) {
        String message = "Could not find file " + inFile.getAbsolutePath();
        if (failonerror) {
          throw new BuildException(message, fnfe, getLocation());
        } else {
          log(message, MSG_WARN);
        }
        return;
      } catch (IOException ioe) {
        String message = "Could not read file " + inFile.getAbsolutePath();
        if (failonerror) {
          throw new BuildException(message, ioe, getLocation());
        } else {
          log(message, MSG_WARN);
        }
        return;
      } finally {
        FileUtils.close(in);
      }
    }
    Enumeration e = propertySets.elements();
    while (e.hasMoreElements()) {
      PropertySet ps = ((PropertySet) (e.nextElement()));
      allProps.putAll(ps.getProperties());
    }
    OutputStream os = null;
    try {
      if (destfile == null) {
        os = new ByteArrayOutputStream();
        saveProperties(allProps, os);
        log(os.toString(), MSG_INFO);
      } else {
        if (destfile.exists() && destfile.isDirectory()) {
          String message = "destfile is a directory!";
          if (failonerror) {
            throw new BuildException(message, getLocation());
          } else {
            log(message, MSG_ERR);
          }
          return;
        }
        if (destfile.exists() && (!destfile.canWrite())) {
          String message = "Can not write to the specified destfile!";
          if (failonerror) {
            throw new BuildException(message, getLocation());
          } else {
            log(message, MSG_ERR);
          }
          return;
        }
        os = new FileOutputStream(this.destfile);
        saveProperties(allProps, os);
      }
    } catch (IOException ioe) {
      if (failonerror) {
        throw new BuildException(ioe, getLocation());
      } else {
        log(ioe.getMessage(), MSG_INFO);
      }
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException ex) {
        }
      }
    }
  }
}
