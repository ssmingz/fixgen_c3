class PlaceHold{
public void addFailure(Test test, Throwable t) {
    if (haltOnFailure) {
        res.stop();
    }
}
}