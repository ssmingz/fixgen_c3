class PlaceHold {
  public void execute() throws TaskException {
    try {
      MailMessage mailMessage = new MailMessage(mailhost);
      mailMessage.setPort(mailport);
      if (from != null) {
        mailMessage.from(from);
      } else {
        throw new TaskException("Attribute \"from\" is required.");
      }
      if (toList != null) {
        StringTokenizer t = new StringTokenizer(toList, ", ", false);
        while (t.hasMoreTokens()) {
          mailMessage.to(t.nextToken());
        }
      } else {
        throw new TaskException("Attribute \"toList\" is required.");
      }
      if (subject != null) {
        mailMessage.setSubject(subject);
      }
      if (!files.isEmpty()) {
        PrintStream out = mailMessage.getPrintStream();
        for (Enumeration e = files.elements(); e.hasMoreElements(); ) {
          File file = ((File) (e.nextElement()));
          if (file.exists() && file.canRead()) {
            int bufsize = 1024;
            int length;
            byte[] buf = new byte[bufsize];
            if (includefilenames) {
              String filename = file.getName();
              int filenamelength = filename.length();
              out.println(filename);
              for (int star = 0; star < filenamelength; star++) {
                out.print('=');
              }
              out.println();
            }
            BufferedInputStream in = null;
            try {
              in = new BufferedInputStream(new FileInputStream(file), bufsize);
              while ((length = in.read(buf, 0, bufsize)) != (-1)) {
                out.write(buf, 0, length);
              }
              if (includefilenames) {
                out.println();
              }
            } finally {
              if (in != null) {
                try {
                  in.close();
                } catch (IOException ioe) {
                }
              }
            }
          } else {
            throw new TaskException(
                ("File \"" + file.getName()) + "\" does not exist or is not readable.");
          }
        }
      } else if (message != null) {
        PrintStream out = mailMessage.getPrintStream();
        out.print(message);
      } else {
        throw new TaskException("Attribute \"file\" or \"message\" is required.");
      }
      log("Sending email");
      mailMessage.sendAndClose();
    } catch (IOException ioe) {
      String err = "IO error sending mail " + ioe.toString();
      if (failOnError) {
        throw new TaskException(err, ioe);
      } else {
        log(err, MSG_ERR);
      }
    }
  }
}
