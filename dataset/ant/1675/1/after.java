class PlaceHold {
  private void sendMail(Values values, String message) throws IOException {
    MailMessage mailMessage = new MailMessage(values.mailhost(), values.port());
    mailMessage.setHeader("Date", DateUtils.getDateForHeader());
    mailMessage.from(values.from());
    if (!values.replytoList().equals("")) {
      StringTokenizer t = new StringTokenizer(values.replytoList(), ", ", false);
      while (t.hasMoreTokens()) {
        mailMessage.replyto(t.nextToken());
      }
    }
    StringTokenizer t = new StringTokenizer(values.toList(), ", ", false);
    while (t.hasMoreTokens()) {
      mailMessage.to(t.nextToken());
    }
    mailMessage.setSubject(values.subject());
    if (values.charset().length() > 0) {
      mailMessage.setHeader(
          "Content-Type", ((values.mimeType() + "; charset=\"") + values.charset()) + "\"");
    } else {
      mailMessage.setHeader("Content-Type", values.mimeType());
    }
    PrintStream ps = mailMessage.getPrintStream();
    ps.println(values.body().length() > 0 ? values.body() : message);
    mailMessage.sendAndClose();
  }
}
