class PlaceHold{
public void test6() {
    Timer timer = new Timer();
    executeTarget("test6");
    timer.stop();
    if (TRACE) {
        System.out.println(" test6 elapsed time=" + timer.time());
    }
    assert timer.time() < 2000;
}
}