class PlaceHold{
public Object nextElement() throws NoSuchElementException {
    if (!hasMoreElements()) {
        throw new NoSuchElementException("OneLiner");
    }
    BufferLine tmpLine = new BufferLine(line.toString(), eolStr.toString());
    nextLine();
    return tmpLine;
}
}