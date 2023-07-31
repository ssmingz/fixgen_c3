class PlaceHold {
  public void send() {
    try {
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", String.valueOf(port));
      Session sesh = Session.getDefaultInstance(props, null);
      MimeMessage msg = new MimeMessage(sesh);
      MimeMultipart attachments = new MimeMultipart();
      if (from.getName() == null) {
        msg.setFrom(new InternetAddress(from.getAddress()));
      } else {
        msg.setFrom(new InternetAddress(from.getAddress(), from.getName()));
      }
      msg.setRecipients(TO, internetAddresses(toList));
      msg.setRecipients(CC, internetAddresses(ccList));
      msg.setRecipients(BCC, internetAddresses(bccList));
      if (subject != null) {
        msg.setSubject(subject);
      }
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream out = new PrintStream(baos);
      message.print(out);
      out.close();
      MimeBodyPart textbody = new MimeBodyPart();
      textbody.setContent(baos.toString(), message.getMimeType());
      attachments.addBodyPart(textbody);
      Enumeration e = files.elements();
      while (e.hasMoreElements()) {
        File file = ((File) (e.nextElement()));
        MimeBodyPart body;
        body = new MimeBodyPart();
        if ((!file.exists()) || (!file.canRead())) {
          throw new BuildException(
              ("File \"" + file.getAbsolutePath()) + "\" does not exist or is not readable.");
        }
        FileDataSource fileData = new FileDataSource(file);
        DataHandler fileDataHandler = new DataHandler(fileData);
        body.setDataHandler(fileDataHandler);
        body.setFileName(file.getName());
        attachments.addBodyPart(body);
      }
      msg.setContent(attachments);
      Transport.send(msg);
    } catch (MessagingException e) {
      throw new BuildException("Problem while sending mime mail:", e);
    } catch (IOException e) {
      throw new BuildException("Problem while sending mime mail:", e);
    }
  }
}
