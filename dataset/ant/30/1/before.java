class PlaceHold{
public void setHaltonfailure(boolean value) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
        BaseTest test = ((BaseTest) (e.nextElement()));
        test.setHaltonfailure(value);
    } 
}
}