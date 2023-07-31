class PlaceHold {
  public void execute() {
    Message savedMessage = message;
    Vector savedFiles = ((Vector) (files.clone()));
    try {
      Mailer mailer = null;
      boolean autoFound = false;
      if (encoding.equals(MIME) || (encoding.equals(AUTO) && (!autoFound))) {
        try {
          mailer =
              ((Mailer)
                  (Class.forName("org.apache.tools.ant.taskdefs.email.MimeMailer").newInstance()));
          autoFound = true;
          log("Using MIME mail", MSG_VERBOSE);
        } catch (Throwable e) {
          log("Failed to initialise MIME mail", MSG_WARN);
        }
      }
      if (encoding.equals(UU) || (encoding.equals(AUTO) && (!autoFound))) {
        try {
          mailer =
              ((Mailer)
                  (Class.forName("org.apache.tools.ant.taskdefs.email.UUMailer").newInstance()));
          autoFound = true;
          log("Using UU mail", MSG_VERBOSE);
        } catch (Throwable e) {
          log("Failed to initialise UU mail", MSG_WARN);
        }
      }
      if (encoding.equals(PLAIN) || (encoding.equals(AUTO) && (!autoFound))) {
        mailer = new PlainMailer();
        autoFound = true;
        log("Using plain mail", MSG_VERBOSE);
      }
      if (mailer == null) {
        throw new BuildException("Failed to initialise encoding: " + encoding);
      }
      if (message == null) {
        message = new Message();
      }
      if ((from == null) || (from.getAddress() == null)) {
        throw new BuildException("A from element is required");
      }
      if ((toList.isEmpty() && ccList.isEmpty()) && bccList.isEmpty()) {
        throw new BuildException("At least one of to,cc or bcc must " + "be supplied");
      }
      if (messageMimeType != null) {
        if (message.isMimeTypeSpecified()) {
          throw new BuildException("The mime type can only be " + "specified in one location");
        } else {
          message.setMimeType(messageMimeType);
        }
      }
      Enumeration e = filesets.elements();
      while (e.hasMoreElements()) {
        FileSet fs = ((FileSet) (e.nextElement()));
        DirectoryScanner ds = fs.getDirectoryScanner(project);
        String[] includedFiles = ds.getIncludedFiles();
        File baseDir = ds.getBasedir();
        for (int j = 0; j < includedFiles.length; ++j) {
          File file = new File(baseDir, includedFiles[j]);
          files.addElement(file);
        }
      }
      log("Sending email: " + subject, MSG_INFO);
      log("From " + from, MSG_VERBOSE);
      log("To " + toList, MSG_VERBOSE);
      log("Cc " + ccList, MSG_VERBOSE);
      log("Bcc " + bccList, MSG_VERBOSE);
      mailer.setHost(host);
      mailer.setPort(port);
      mailer.setMessage(message);
      mailer.setFrom(from);
      mailer.setToList(toList);
      mailer.setCcList(ccList);
      mailer.setBccList(bccList);
      mailer.setFiles(files);
      mailer.setSubject(subject);
      mailer.setTask(this);
      mailer.setIncludeFileNames(includeFileNames);
      mailer.send();
      int count = files.size();
      log((("Sent email with " + count) + " attachment") + (count == 1 ? "" : "s"), MSG_INFO);
    } catch (BuildException e) {
      log("Failed to send email", MSG_WARN);
      if (failOnError) {
        throw e;
      }
    } finally {
      message = savedMessage;
      files = savedFiles;
    }
  }
}
