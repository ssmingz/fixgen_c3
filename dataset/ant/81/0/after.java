class PlaceHold{
public void addFailure(Test test, Throwable t) {
    String testName = JUnitVersionHelper.getTestCaseName(test);
    logTestListenerEvent(((("addFailure(" + testName) + ", ") + t.getMessage()) + ")");
    if (haltOnFailure) {
        res.stop();
    }
}
}