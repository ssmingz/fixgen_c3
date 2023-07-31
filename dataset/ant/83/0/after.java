class PlaceHold{
public void execute() throws BuildException {
    if (getHost() == null) {
        throw new BuildException("Host is required.");
    }
    if (getUserInfo().getName() == null) {
        throw new BuildException("Username is required.");
    }
    if ((getUserInfo().getKeyfile() == null) && (getUserInfo().getPassword() == null)) {
        throw new BuildException("Password or Keyfile is required.");
    }
    if (command == null) {
        throw new BuildException("Command is required.");
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Tee tee = new Tee(out, System.out);
    try {
        Session session = openSession();
        session.setTimeout(((int) (maxwait)));
        final ChannelExec channel = ((ChannelExec) (session.openChannel("exec")));
        channel.setCommand(command);
        channel.setOutputStream(tee);
        channel.connect();
        thread = new Thread() {
            public void run() {
                while (!channel.isEOF()) {
                    if (thread == null) {
                        return;
                    }
                    try {
                        sleep(500);
                    } catch (Exception e) {
                    }
                } 
            }
        };
        thread.start();
        thread.join(maxwait);
        if (thread.isAlive()) {
            thread = null;
            log("Timeout period exceeded, connection dropped.");
        } else {
            if (output_property != null) {
                getProject().setProperty(output_property, out.toString());
            }
            if (output_file != null) {
                writeToFile(out.toString(), append, output_file);
            }
        }
    } catch (Exception e) {
        if (getFailonerror()) {
            throw new BuildException(e);
        } else {
            log("Caught exception: " + e.getMessage(), MSG_ERR);
        }
    }
}
}