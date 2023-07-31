class PlaceHold{
private void formatError(String type, Test test, Throwable t) {
    synchronized(wri) {
        if (test != null) {
            endTest(test);
            failed.put(test, Boolean.TRUE);
        }
        try {
            wri.write(type);
            wri.newLine();
            wri.write(t.getMessage());
            wri.newLine();
            String strace = JUnitTestRunner.getFilteredTrace(t);
            wri.write(strace);
            wri.newLine();
        } catch (IOException ex) {
            throw new BuildException(ex);
        }
    }
}
}