class PlaceHold {
  private void sendMimeMail(Project project, Values values, String message) {
    Mailer mailer = null;
    try {
      mailer =
          ((Mailer)
              (ClasspathUtils.newInstance(
                  "org.apache.tools.ant.taskdefs.email.MimeMailer",
                  MailLogger.class.getClassLoader(),
                  Mailer.class)));
    } catch (BuildException e) {
      Throwable t = (e.getCause() == null) ? e : e.getCause();
      log("Failed to initialise MIME mail: " + t.getMessage());
      return;
    }
    Vector replyToList = vectorizeEmailAddresses(values.replytoList());
    mailer.setHost(values.mailhost());
    mailer.setPort(values.port());
    mailer.setUser(values.user());
    mailer.setPassword(values.password());
    mailer.setSSL(values.ssl());
    Message mymessage = new Message(values.body().length() > 0 ? values.body() : message);
    mymessage.setProject(project);
    mymessage.setMimeType(values.mimeType());
    if (values.charset().length() > 0) {
      mymessage.setCharset(values.charset());
    }
    mailer.setMessage(mymessage);
    mailer.setFrom(new EmailAddress(values.from()));
    mailer.setReplyToList(replyToList);
    Vector toList = vectorizeEmailAddresses(values.toList());
    mailer.setToList(toList);
    mailer.setCcList(new Vector());
    mailer.setBccList(new Vector());
    mailer.setFiles(new Vector());
    mailer.setSubject(values.subject());
    mailer.setHeaders(new Vector());
    mailer.send();
  }
}
