class PlaceHold {
  public void execute() throws TaskException {
    sqlCommand = sqlCommand.trim();
    if (((srcFile == null) && (sqlCommand.length() == 0)) && filesets.isEmpty()) {
      if (transactions.size() == 0) {
        throw new TaskException(
            "Source file or fileset, transactions or sql statement must be set!");
      }
    } else {
      for (int i = 0; i < filesets.size(); i++) {
        FileSet fs = ((FileSet) (filesets.get(i)));
        DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
        File srcDir = fs.getDir();
        String[] srcFiles = ds.getIncludedFiles();
        for (int j = 0; j < srcFiles.length; j++) {
          Transaction t = createTransaction();
          t.setSrc(new File(srcDir, srcFiles[j]));
        }
      }
      Transaction t = createTransaction();
      t.setSrc(srcFile);
      t.addContent(sqlCommand);
    }
    if (driver == null) {
      throw new TaskException("Driver attribute must be set!");
    }
    if (userId == null) {
      throw new TaskException("User Id attribute must be set!");
    }
    if (password == null) {
      throw new TaskException("Password attribute must be set!");
    }
    if (url == null) {
      throw new TaskException("Url attribute must be set!");
    }
    if ((srcFile != null) && (!srcFile.exists())) {
      throw new TaskException("Source file does not exist!");
    }
    Driver driverInstance = null;
    try {
      Class dc;
      if (classpath != null) {
        getLogger()
            .debug((("Loading " + driver) + " using AntClassLoader with classpath ") + classpath);
        final URL[] urls = PathUtil.toURLs(classpath);
        final ClassLoader classLoader = new URLClassLoader(urls);
        dc = classLoader.loadClass(driver);
      } else {
        getLogger().debug(("Loading " + driver) + " using system loader.");
        dc = Class.forName(driver);
      }
      driverInstance = ((Driver) (dc.newInstance()));
    } catch (ClassNotFoundException e) {
      throw new TaskException(("Class Not Found: JDBC driver " + driver) + " could not be loaded");
    } catch (IllegalAccessException e) {
      throw new TaskException(("Illegal Access: JDBC driver " + driver) + " could not be loaded");
    } catch (InstantiationException e) {
      throw new TaskException(
          ("Instantiation Exception: JDBC driver " + driver) + " could not be loaded");
    }
    try {
      getLogger().debug("connecting to " + url);
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
          getLogger().debug("Opening PrintStream to output file " + output);
          out = new PrintStream(new BufferedOutputStream(new FileOutputStream(output)));
        }
        for (Iterator e = transactions.iterator(); e.hasNext(); ) {
          ((Transaction) (e.next())).runTransaction(out);
          if (!autocommit) {
            getLogger().debug("Commiting transaction");
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
      throw new TaskException("Error", e);
    } catch (SQLException e) {
      if (((!autocommit) && (conn != null)) && onError.equals("abort")) {
        try {
          conn.rollback();
        } catch (SQLException ex) {
        }
      }
      throw new TaskException("Error", e);
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
    getLogger().info(((goodSql + " of ") + totalSql) + " SQL statements executed successfully");
  }
}
