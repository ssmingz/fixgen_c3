class PlaceHold {
  @Test
  public void testAsciiCharset() throws InterruptedException {
    ServerThread testMailServer = new ServerThread();
    Thread server = new Thread(testMailServer);
    server.start();
    ClientThread testMailClient = new ClientThread();
    testMailClient.from("Mail Message <EmailTaskTest@ant.apache.org>");
    testMailClient.to("Ceki Gülcü <abuse@mail-abuse.org>");
    testMailClient.setSubject("Test subject");
    testMailClient.setMessage("");
    Thread client = new Thread(testMailClient);
    client.start();
    server.join(60 * 1000);
    client.join(30 * 1000);
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
                                                                    + "RCPT TO:"
                                                                    + " <abuse@mail-abuse.org>\r\n")
                                                                + "250\r\n")
                                                            + "DATA\r\n")
                                                        + "354\r\n")
                                                    + "Subject: Test subject\r\n")
                                                + "From: Mail Message"
                                                + " <EmailTaskTest@ant.apache.org>\r\n")
                                            + "To: Ceki Gülcü <abuse@mail-abuse.org>\r\n")
                                        + "X-Mailer: org.apache.tools.mail.MailMessage"
                                        + " (ant.apache.org)\r\n")
                                    + "\r\n")
                                + "\r\n")
                            + "\r\n")
                        + ".\r\n")
                    + "250\r\n")
                + "QUIT\r\n")
            + "221\r\n";
    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
    PrintStream bos1 = new PrintStream(baos1, true);
    PrintStream bos2 = new PrintStream(baos2, true);
    bos1.print(expectedResult);
    bos2.print(result);
    assertEquals(
        "expected message length != actual message length " + "in testAsciiCharset()",
        expectedResult.length(),
        result.length());
    assertEquals(
        "baos1 and baos2 should be the same in testAsciiCharset()",
        baos1.toString(),
        baos2.toString());
    assertFalse(testMailClient.getFailMessage(), testMailClient.isFailed());
  }
}
