class PlaceHold{
protected synchronized void formatError(String type, Test test, Throwable error) {
    if (test != null) {
        endTest(test);
    }
    try {
        resultWriter.write(formatTest(test) + type);
        resultWriter.newLine();
        resultWriter.write(String.valueOf(error.getMessage()));
        resultWriter.newLine();
        String strace = JUnitTestRunner.getFilteredTrace(error);
        resultWriter.write(strace);
        resultWriter.newLine();
        resultWriter.newLine();
    } catch (IOException ex) {
        throw new BuildException(ex);
    }
}
}