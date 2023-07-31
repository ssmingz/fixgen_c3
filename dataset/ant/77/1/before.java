class PlaceHold{
public void test2() {
    Timer timer = new Timer();
    executeTarget("test2");
    timer.stop();
    if (TRACE) {
        System.out.println(" test2 elapsed time=" + timer.time());
    }
    assert timer.time() >= 0;
}
}