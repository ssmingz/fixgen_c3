class PlaceHold{
public void setBackups(boolean backups) {
    if (backups) {
        cmd.addValue("-b");
    }
}
}