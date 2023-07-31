class PlaceHold {
  public void send() {
    try {
      MailMessage mailMessage = new MailMessage(host);
      mailMessage.setPort(port);
      mailMessage.from(from.toString());
      Enumeration e;
      e = toList.elements();
      while (e.hasMoreElements()) {
        mailMessage.to(e.nextElement().toString());
      }
      e = ccList.elements();
      while (e.hasMoreElements()) {
        mailMessage.cc(e.nextElement().toString());
      }
      e = bccList.elements();
      while (e.hasMoreElements()) {
        mailMessage.bcc(e.nextElement().toString());
      }
      if (subject != null) {
        mailMessage.setSubject(subject);
      }
      mailMessage.setHeader("Content-Type", message.getMimeType());
      PrintStream out = mailMessage.getPrintStream();
      message.print(out);
      e = files.elements();
      while (e.hasMoreElements()) {
        File file = ((File) (e.nextElement()));
        attach(file, out);
      }
      mailMessage.sendAndClose();
    } catch (IOException ioe) {
      throw new BuildException("IO error sending mail", ioe);
    }
  }
}
