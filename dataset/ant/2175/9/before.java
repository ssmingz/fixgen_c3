class PlaceHold {
  public void doMail() throws MessagingException, AddressException, TaskException {
    Properties props = new Properties();
    props.put("mail.smtp.host", mailhost);
    Session sesh = Session.getDefaultInstance(props, null);
    MimeMessage msg = new MimeMessage(sesh);
    log("message sender: " + from, MSG_VERBOSE);
    msg.setFrom(new InternetAddress(from));
    addRecipients(msg, TO, "To", toList);
    addRecipients(msg, CC, "Cc", ccList);
    addRecipients(msg, BCC, "Bcc", bccList);
    if (subject != null) {
      log("subject: " + subject, MSG_VERBOSE);
      msg.setSubject(subject);
    }
    MimeMultipart attachments = new MimeMultipart();
    if (messageFile != null) {
      int size = ((int) (messageFile.length()));
      byte data[] = new byte[size];
      try {
        FileInputStream inStream = new FileInputStream(messageFile);
        inStream.read(data);
        inStream.close();
        message = new String(data);
      } catch (IOException e) {
        throw new TaskException("Error", e);
      }
    }
    if (message != null) {
      MimeBodyPart textbody = new MimeBodyPart();
      textbody.setContent(message, messageMimeType);
      attachments.addBodyPart(textbody);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      if (fs != null) {
        DirectoryScanner ds = fs.getDirectoryScanner(project);
        String[] dsfiles = ds.getIncludedFiles();
        File baseDir = ds.getBasedir();
        for (int j = 0; j < dsfiles.length; j++) {
          File file = new File(baseDir, dsfiles[j]);
          MimeBodyPart body;
          body = new MimeBodyPart();
          if ((!file.exists()) || (!file.canRead())) {
            throw new TaskException(
                ("File \"" + file.getAbsolutePath()) + "\" does not exist or is not readable.");
          }
          log(((("Attaching " + file.toString()) + " - ") + file.length()) + " bytes", MSG_VERBOSE);
          FileDataSource fileData = new FileDataSource(file);
          DataHandler fileDataHandler = new DataHandler(fileData);
          body.setDataHandler(fileDataHandler);
          body.setFileName(file.getName());
          attachments.addBodyPart(body);
        }
      }
    }
    msg.setContent(attachments);
    log("sending email ");
    Transport.send(msg);
  }
}
