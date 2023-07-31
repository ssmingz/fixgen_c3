class PlaceHold{
public void initChecksum() {
    if (checksum != null) {
        return;
    }
    if ("CRC".equalsIgnoreCase(algorithm)) {
        checksum = new CRC32();
    } else if ("ADLER".equalsIgnoreCase(algorithm)) {
        checksum = new Adler32();
    } else {
        throw new BuildException(new NoSuchAlgorithmException());
    }
}
}