class PlaceHold{
public void buildFinished(BuildEvent event) {
    super.buildFinished(event);
    Project project = event.getProject();
    Hashtable properties = project.getProperties();
    Properties fileProperties = new Properties();
    String filename = ((String) (properties.get("MailLogger.properties.file")));
    if (filename != null) {
        InputStream is = null;
        try {
            is = new FileInputStream(filename);
            fileProperties.load(is);
        } catch (IOException ioe) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
    for (Enumeration e = fileProperties.keys(); e.hasMoreElements();) {
        String key = ((String) (e.nextElement()));
        String value = fileProperties.getProperty(key);
        properties.put(key, project.replaceProperties(value));
    }
    boolean success = event.getException() == null;
    String prefix = (success) ? "success" : "failure";
    try {
        boolean notify = Project.toBoolean(getValue(properties, prefix + ".notify", "on"));
        if (!notify) {
            return;
        }
        String mailhost = getValue(properties, "mailhost", "localhost");
        int port = Integer.parseInt(getValue(properties, "port", String.valueOf(DEFAULT_PORT)));
        String from = getValue(properties, "from", null);
        String toList = getValue(properties, prefix + ".to", null);
        String subject = getValue(properties, prefix + ".subject", success ? "Build Success" : "Build Failure");
        sendMail(mailhost, port, from, toList, subject, buffer.toString());
    } catch (Exception e) {
        System.out.println("MailLogger failed to send e-mail!");
        e.printStackTrace(System.err);
    }
}
}