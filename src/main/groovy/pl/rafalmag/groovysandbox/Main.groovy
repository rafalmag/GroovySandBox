package pl.rafalmag.groovysandbox

class Main {

    public static void main(String[] args) {
        println "hello"
        def map = new Spreadsheet().getMap()
        println(map)

        new RestFull().call()
    }

}
