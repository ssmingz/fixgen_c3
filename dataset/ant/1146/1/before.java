class PlaceHold {
  public void execute() {
    Message savedMessage = message;
    try {
      Mailer mailer = null;
      boolean autoFound = false;
      if (encoding.equals(MIME) || (encoding.equals(AUTO) && (!autoFound))) {
        try {
          Class.forName("javax.activation.DataHandler");
          Class.forName("javax.mail.internet.MimeMessage");
          mailer =
              ((Mailer)
                  (ClasspathUtils.newInstance(
                      "org.apache.tools.ant.taskdefs.email.MimeMailer",
                      EmailTask.class.getClassLoader(),
                      Mailer.class)));
          autoFound = true;
          log("Using MIME mail", MSG_VERBOSE);
        } catch (BuildException e) {
          logBuildException("Failed to initialise MIME mail: ", e);
        }
      }
      if (((!autoFound) && ((user != null) || (password != null)))
          && (encoding.equals(UU) || encoding.equals(PLAIN))) {
        throw new BuildException("SMTP auth only possible with MIME mail");
      }
      if (((!autoFound) && (ssl || starttls)) && (encoding.equals(UU) || encoding.equals(PLAIN))) {
        throw new BuildException("SSL and STARTTLS only possible with" + " MIME mail");
      }
      if (encoding.equals(UU) || (encoding.equals(AUTO) && (!autoFound))) {
        try {
          mailer =
              ((Mailer)
                  (ClasspathUtils.newInstance(
                      "org.apache.tools.ant.taskdefs.email.UUMailer",
                      EmailTask.class.getClassLoader(),
                      Mailer.class)));
          autoFound = true;
          log("Using UU mail", MSG_VERBOSE);
        } catch (BuildException e) {
          logBuildException("Failed to initialise UU mail: ", e);
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
        message.setProject(getProject());
      }
      if ((from == null) || (from.getAddress() == null)) {
        throw new BuildException("A from element is required");
      }
      if ((toList.isEmpty() && ccList.isEmpty()) && bccList.isEmpty()) {
        throw new BuildException("At least one of to, cc or bcc must " + "be supplied");
      }
      if (messageMimeType != null) {
        if (message.isMimeTypeSpecified()) {
          throw new BuildException("The mime type can only be " + "specified in one location");
        }
        message.setMimeType(messageMimeType);
      }
      if (charset != null) {
        if (message.getCharset() != null) {
          throw new BuildException("The charset can only be " + "specified in one location");
        }
        message.setCharset(charset);
      }
      Vector files = new Vector();
      if (attachments != null) {
        Iterator iter = attachments.iterator();
        while (iter.hasNext()) {
          files.addElement(((FileProvider) (iter.next())).getFile());
        }
      }
      log("Sending email: " + subject, MSG_INFO);
      log("From " + from, MSG_VERBOSE);
      log("ReplyTo " + replyToList, MSG_VERBOSE);
      log("To " + toList, MSG_VERBOSE);
      log("Cc " + ccList, MSG_VERBOSE);
      log("Bcc " + bccList, MSG_VERBOSE);
      mailer.setHost(host);
      mailer.setPort(port);
      mailer.setUser(user);
      mailer.setPassword(password);
      mailer.setSSL(ssl);
      mailer.setEnableStartTLS(starttls);
      mailer.setMessage(message);
      mailer.setFrom(from);
      mailer.setReplyToList(replyToList);
      mailer.setToList(toList);
      mailer.setCcList(ccList);
      mailer.setBccList(bccList);
      mailer.setFiles(files);
      mailer.setSubject(subject);
      mailer.setTask(this);
      mailer.setIncludeFileNames(includeFileNames);
      mailer.setHeaders(headers);
      mailer.setIgnoreInvalidRecipients(ignoreInvalidRecipients);
      mailer.send();
      int count = files.size();
      log((("Sent email with " + count) + " attachment") + (count == 1 ? "" : "s"), MSG_INFO);
    } catch (BuildException e) {
      logBuildException("Failed to send email: ", e);
      if (failOnError) {
        throw e;
      }
    } catch (Exception e) {
      log("Failed to send email: " + e.getMessage(), MSG_WARN);
      if (failOnError) {
        throw new BuildException(e);
      }
    } finally {
      message = savedMessage;
    }
  }
}
