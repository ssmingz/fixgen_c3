class PlaceHold{
public void addError(Test test, Throwable t) {
    String testName = JUnitVersionHelper.getTestCaseName(test);
    logTestListenerEvent(((("addError(" + testName) + ", ") + t.getMessage()) + ")");
    if (haltOnError) {
        res.stop();
    }
}
}