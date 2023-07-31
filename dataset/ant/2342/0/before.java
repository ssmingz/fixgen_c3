class PlaceHold {
  public void testEmptyBody() {
    ServerThread testMailServer = new ServerThread();
    Thread server = new Thread(testMailServer);
    server.start();
    ClientThread testMailClient = new ClientThread();
    testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
    testMailClient.to("to@you.com");
    testMailClient.setSubject("Test subject");
    testMailClient.setMessage("");
    Thread client = new Thread(testMailClient);
    client.start();
    try {
      server.join(60 * 1000);
      client.join(30 * 1000);
    } catch (InterruptedException ie) {
      fail("InterruptedException: " + ie);
    }
    String result = testMailServer.getResult();
    String expectedResult =
        (((((((((((((((((((((((("220 test SMTP EmailTaskTest\r\n" + "HELO ") + local) + "\r\n")
                                                                                                + "250 ")
                                                                                            + local)
                                                                                        + " Hello ")
                                                                                    + local)
                                                                                + " [127.0.0.1],"
                                                                                + " pleased to meet"
                                                                                + " you\r\n")
                                                                            + "MAIL FROM:"
                                                                            + " <EmailTaskTest@ant.apache.org>\r\n")
                                                                        + "250\r\n")
                                                                    + "RCPT TO: <to@you.com>\r\n")
                                                                + "250\r\n")
                                                            + "DATA\r\n")
                                                        + "354\r\n")
                                                    + "Subject: Test subject\r\n")
                                                + "From: Mail Message"
                                                + " <EmailTaskTest@ant.apache.org>\r\n")
                                            + "To: to@you.com\r\n")
                                        + "X-Mailer: org.apache.tools.mail.MailMessage"
                                        + " (ant.apache.org)\r\n")
                                    + "\r\n")
                                + "\r\n")
                            + "\r\n")
                        + ".\r\n")
                    + "250\r\n")
                + "QUIT\r\n")
            + "221\r\n";
    assertEquals(expectedResult.length(), result.length());
    assertEquals(expectedResult, result);
    if (testMailClient.isFailed()) {
      fail(testMailClient.getFailMessage());
    }
  }
}
