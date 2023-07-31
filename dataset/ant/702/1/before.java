class PlaceHold {
  public void execute() throws BuildException {
    Vector savedTransaction = ((Vector) (transactions.clone()));
    String savedSqlCommand = sqlCommand;
    sqlCommand = sqlCommand.trim();
    try {
      if (((srcFile == null) && (sqlCommand.length() == 0)) && (resources == null)) {
        if (transactions.size() == 0) {
          throw new BuildException(
              ("Source file or resource collection, " + "transactions or sql statement ")
                  + "must be set!",
              getLocation());
        }
      }
      if ((srcFile != null) && (!srcFile.isFile())) {
        throw new BuildException(("Source file " + srcFile) + " is not a file!", getLocation());
      }
      if (resources != null) {
        Iterator iter = resources.iterator();
        while (iter.hasNext()) {
          Resource r = ((Resource) (iter.next()));
          Transaction t = createTransaction();
          t.setSrcResource(r);
        }
      }
      Transaction t = createTransaction();
      t.setSrc(srcFile);
      t.addText(sqlCommand);
      if (getConnection() == null) {
        return;
      }
      try {
        PrintStream out = new PrintStream(new KeepAliveOutputStream(System.out));
        try {
          if (output != null) {
            log("Opening PrintStream to output Resource " + output, MSG_VERBOSE);
            OutputStream os = null;
            FileProvider fp = ((FileProvider) (output.as(FileProvider.class)));
            if (fp != null) {
              os = new FileOutputStream(fp.getFile(), append);
            } else {
              if (append) {
                Appendable a = ((Appendable) (output.as(Appendable.class)));
                if (a != null) {
                  os = a.getAppendOutputStream();
                }
              }
              if (os == null) {
                os = output.getOutputStream();
                if (append) {
                  log(
                      ("Ignoring append=true for non-appendable" + " resource ") + output,
                      MSG_WARN);
                }
              }
            }
            out = new PrintStream(new BufferedOutputStream(os));
          }
          for (Enumeration e = transactions.elements(); e.hasMoreElements(); ) {
            ((Transaction) (e.nextElement())).runTransaction(out);
            if (!isAutocommit()) {
              log("Committing transaction", MSG_VERBOSE);
              getConnection().commit();
            }
          }
        } finally {
          FileUtils.close(out);
        }
      } catch (IOException e) {
        closeQuietly();
        if (onError.equals("abort")) {
          throw new BuildException(e, getLocation());
        }
      } catch (SQLException e) {
        closeQuietly();
        if (onError.equals("abort")) {
          throw new BuildException(e, getLocation());
        }
      } finally {
        try {
          if (getStatement() != null) {
            getStatement().close();
          }
        } catch (SQLException ex) {
        }
        try {
          if (getConnection() != null) {
            getConnection().close();
          }
        } catch (SQLException ex) {
        }
      }
      log(((goodSql + " of ") + totalSql) + " SQL statements executed successfully");
    } finally {
      transactions = savedTransaction;
      sqlCommand = savedSqlCommand;
    }
  }
}
