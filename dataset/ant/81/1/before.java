class PlaceHold{
public void addError(Test test, Throwable t) {
    if (haltOnError) {
        res.stop();
    }
}
}