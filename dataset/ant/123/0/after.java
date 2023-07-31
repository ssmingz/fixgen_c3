class PlaceHold {
  public void execute() throws BuildException {
    sqlCommand = sqlCommand.trim();
    if (((srcFile == null) && (sqlCommand.length() == 0)) && filesets.isEmpty()) {
      if (transactions.size() == 0) {
        throw new BuildException(
            "Source file or fileset, transactions or sql statement must be set!", location);
      }
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.elementAt(i)));
        DirectoryScanner ds = fs.getDirectoryScanner(project);
        File srcDir = fs.getDir(project);
        String[] srcFiles = ds.getIncludedFiles();
        for (int j = 0; j < srcFiles.length; j++) {
          Transaction t = createTransaction();
          t.setSrc(new File(srcDir, srcFiles[j]));
        }
      }
      Transaction t = createTransaction();
      t.setSrc(srcFile);
      t.addText(sqlCommand);
    }
    if (driver == null) {
      throw new BuildException("Driver attribute must be set!", location);
    }
    if (userId == null) {
      throw new BuildException("User Id attribute must be set!", location);
    }
    if (password == null) {
      throw new BuildException("Password attribute must be set!", location);
    }
    if (url == null) {
      throw new BuildException("Url attribute must be set!", location);
    }
    if ((srcFile != null) && (!srcFile.exists())) {
      throw new BuildException("Source file does not exist!", location);
    }
    Driver driverInstance = null;
    try {
      Class dc;
      if (classpath != null) {
        synchronized (loaderMap) {
          if (caching) {
            loader = ((AntClassLoader) (loaderMap.get(driver)));
          }
          if (loader == null) {
            log(
                (("Loading " + driver) + " using AntClassLoader with classpath ") + classpath,
                MSG_VERBOSE);
            loader = new AntClassLoader(project, classpath);
            if (caching) {
              loaderMap.put(driver, loader);
            }
          } else {
            log(("Loading " + driver) + " using a cached AntClassLoader.", MSG_VERBOSE);
          }
        }
        dc = loader.loadClass(driver);
      } else {
        log(("Loading " + driver) + " using system loader.", MSG_VERBOSE);
        dc = Class.forName(driver);
      }
      driverInstance = ((Driver) (dc.newInstance()));
    } catch (ClassNotFoundException e) {
      throw new BuildException(
          ("Class Not Found: JDBC driver " + driver) + " could not be loaded", location);
    } catch (IllegalAccessException e) {
      throw new BuildException(
          ("Illegal Access: JDBC driver " + driver) + " could not be loaded", location);
    } catch (InstantiationException e) {
      throw new BuildException(
          ("Instantiation Exception: JDBC driver " + driver) + " could not be loaded", location);
    }
    try {
      log("connecting to " + url, MSG_VERBOSE);
      Properties info = new Properties();
      info.put("user", userId);
      info.put("password", password);
      conn = driverInstance.connect(url, info);
      if (conn == null) {
        throw new SQLException("No suitable Driver for " + url);
      }
      if (!isValidRdbms(conn)) {
        return;
      }
      conn.setAutoCommit(autocommit);
      statement = conn.createStatement();
      PrintStream out = System.out;
      try {
        if (output != null) {
          log("Opening PrintStream to output file " + output, MSG_VERBOSE);
          out =
              new PrintStream(
                  new BufferedOutputStream(new FileOutputStream(output.getAbsolutePath(), append)));
        }
        for (Enumeration e = transactions.elements(); e.hasMoreElements(); ) {
          ((Transaction) (e.nextElement())).runTransaction(out);
          if (!autocommit) {
            log("Commiting transaction", MSG_VERBOSE);
            conn.commit();
          }
        }
      } finally {
        if ((out != null) && (out != System.out)) {
          out.close();
        }
      }
    } catch (IOException e) {
      if (((!autocommit) && (conn != null)) && onError.equals("abort")) {
        try {
          conn.rollback();
        } catch (SQLException ex) {
        }
      }
      throw new BuildException(e, location);
    } catch (SQLException e) {
      if (((!autocommit) && (conn != null)) && onError.equals("abort")) {
        try {
          conn.rollback();
        } catch (SQLException ex) {
        }
      }
      throw new BuildException(e, location);
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
      }
    }
    log(((goodSql + " of ") + totalSql) + " SQL statements executed successfully");
  }
}
