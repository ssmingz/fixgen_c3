class PlaceHold{
public void test1() {
    Timer timer = new Timer();
    executeTarget("test1");
    timer.stop();
    if (TRACE) {
        System.out.println(" test1 elapsed time=" + timer.time());
    }
    assertTrue(timer.time() >= 0);
}
}